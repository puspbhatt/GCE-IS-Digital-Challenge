package com.gcesolutions.shopmanager.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.gcesolutions.shopmanager.model.Address;

/**
 * 
 * @author Nand Joshi
 *
 */
public class GeoLocationUtil {
	/**
	 * Retrieves the longitude and latitude of the given address using API key.
	 * 
	 * @param address
	 *            The address to find the Geo-location
	 * @param apiKey
	 *            The Api key to call the google API
	 * @return The instance of {@link GeoPoint} and null if failed to get the
	 *         longitude and latitude of the given address
	 */
	public static GeoPoint getGeoLocationFromAddress(final String address, final String apiKey) {

		try {
			HttpGet httpGet = new HttpGet("https://maps.googleapis.com/maps/api/geocode/json?address="
					+ URLEncoder.encode(address, "UTF-8") + "&key=" + apiKey);

			HttpClient client = HttpClientBuilder.create().build();
			HttpResponse httpResponse = client.execute(httpGet);

			HttpEntity entity = httpResponse.getEntity();
			String responseString = EntityUtils.toString(entity, StandardCharsets.UTF_8);

			System.out.println(responseString);

			JSONObject jsonObject = new JSONObject(responseString);

			BigDecimal longitude = ((JSONArray) jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry")
					.getJSONObject("location").getBigDecimal("lng");
			BigDecimal latitude = ((JSONArray) jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry")
					.getJSONObject("location").getBigDecimal("lat");
			return new GeoPoint(longitude, latitude);

		} catch (ParseException | IOException | JSONException ex) {
			System.out.println("Failed to extract response." + ex);
		}

		return null;
	}

	/**
	 * Retrieves the address from the given latitude and longitude using API key.
	 * 
	 * @param latitude
	 * @param longitude
	 * @param apiKey
	 *            The API key to call the google API
	 * @return The instance of {@link Address}
	 */
	public static Address getAddressFromGeoLocation(final String latitude, final String longitude,
			final String apiKey) {

		try {
			HttpGet httpGet = new HttpGet("https://maps.googleapis.com/maps/api/geocode/json?latlng=" + latitude + ","
					+ longitude + "&key=" + apiKey);

			HttpClient client = HttpClientBuilder.create().build();
			HttpResponse httpResponse = client.execute(httpGet);

			HttpEntity entity = httpResponse.getEntity();
			String responseString = EntityUtils.toString(entity, StandardCharsets.UTF_8);

			System.out.println(responseString);

			JSONObject jsonObject = new JSONObject(responseString);
			if ("OK".equals(jsonObject.getString("status"))) {
				String address = ((JSONArray) jsonObject.get("results")).getJSONObject(0)
						.getString("formatted_address");
				return getAddress(address);
			}

		} catch (ParseException | JSONException | IOException ex) {
			System.out.println("Failed to extract response." + ex);
		}

		return null;
	}

	private static Address getAddress(String formattedAddress) {
		Address address = new Address();
		address.setAddress(formattedAddress);
		return address;
	}

	/**
	 * Represents the coordinate for the address. Contains longitude and latitude
	 * values.
	 * 
	 * @author Nand Joshi
	 *
	 */
	public static class GeoPoint {
		private final BigDecimal longitude;
		private final BigDecimal latitude;

		public GeoPoint(BigDecimal longitude, BigDecimal latitude) {
			this.longitude = longitude;
			this.latitude = latitude;
		}

		public BigDecimal getLongitude() {
			return longitude;
		}

		public BigDecimal getLatitude() {
			return latitude;
		}

		@Override
		public String toString() {
			return "GeoPoint [longitude=" + getLongitude() + ", latitude=" + getLatitude() + "]";
		}

	}
}
