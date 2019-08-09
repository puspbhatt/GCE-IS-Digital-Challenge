package com.gcesolutions.shopmanager.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gcesolutions.shopmanager.model.Address;
import com.gcesolutions.shopmanager.model.Shop;
import com.gcesolutions.shopmanager.service.ShopService;
import com.gcesolutions.shopmanager.util.GeoLocationUtil;
import com.gcesolutions.shopmanager.util.GeoLocationUtil.GeoPoint;

/**
 * Contains the REST end point for the followings:
 * <li>To retrieve all shops</li>
 * <li>To search shops by name or near by location</li>
 * <li>To add new shop</li>
 * 
 * @author Nand Joshi
 *
 */
@RestController
public class ShopController {

	@Autowired
	ShopService shopService;

	@Value("${google.api.key}")
	private String googleApiKey;

	/**
	 * Retrieves the list of all available shops.
	 * 
	 * @return The list of {@link Shop}
	 */
	@GetMapping("/shops")
	public List<Shop> findAll() {
		List<Shop> shops = shopService.findAll();
		assignAddress(shops);
		System.out.println(shops);
		return shops;
	}

	/**
	 * This end point is used to search the shops based on search key. If the search
	 * key is 'Name' then it will return the list of matching shop name. If shearch
	 * key is 'Location' then it will list the near by shops based on the given
	 * address as search value.
	 * 
	 * @param key
	 *            The search by key (either Name or Location)
	 * @param value
	 *            The search value
	 * @return The List of {@link Shop}
	 */

	@GetMapping("/shops/{key}")
	@ResponseBody
	public List<Shop> search(@PathVariable(name = "key") String key, @RequestParam(name = "value") String value) {
		List<Shop> shops = new ArrayList<>();
		if ("Name".equals(key)) {
			shops = shopService.searchByName(value);
			assignAddress(shops);
		} else {
			shops = shopService.findAll();
			assignAddress(shops);

			final Shop myLocation = new Shop();

			GeoPoint result = GeoLocationUtil.getGeoLocationFromAddress(value, googleApiKey);
			if (result != null) {
				myLocation.setLatitude(result.getLatitude().toPlainString());
				myLocation.setLongitude(result.getLatitude().toPlainString());

				shops = getClosest(myLocation, shops, shops.size());
			}
		}
		System.out.println(shops);
		return shops;
	}

	/**
	 * This end point is used to add new shop in the system.
	 * 
	 * @param shop
	 *            The {@link Shop}
	 */
	@PostMapping("/shops")
	public void save(@RequestBody Shop shop) {
		String address = shop.getAddress().toString();

		GeoPoint result = GeoLocationUtil.getGeoLocationFromAddress(address, googleApiKey);
		if (result != null) {
			shop.setLatitude(result.getLatitude().toPlainString());
			shop.setLongitude(result.getLongitude().toPlainString());
		}
		shopService.save(shop);
	}

	private List<Shop> getClosest(final Shop source, final List<Shop> others, int maxToGrab) {
		Collections.sort(others, source);
		return others.subList(0, Math.min(maxToGrab, others.size()));
	}

	private void assignAddress(List<Shop> shops) {
		for (Shop shop : shops) {
			Address address = GeoLocationUtil.getAddressFromGeoLocation(shop.getLatitude(),
					shop.getLongitude(), googleApiKey);
			if (address != null) {
				shop.setAddress(address);
			} else {
				shop.setAddress(addressNotFound());
			}
		}
	}

	private Address addressNotFound() {
		Address temp = new Address();
		temp.setAddress("Not Found");
		return temp;
	}
}
