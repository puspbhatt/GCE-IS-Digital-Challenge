package com.gcesolutions.shopmanager.service;

import java.util.List;

import com.gcesolutions.shopmanager.model.Shop;

/**
 * 
 * @author Nand Joshi
 *
 */
public interface ShopService {
	/**
	 * Retrieves the list of all shops/stores/Mall
	 * 
	 * @return The list of {@link Shop}
	 */
	public List<Shop> findAll();

	/**
	 * Adds new Shop object
	 * 
	 * @param shop
	 *            The instance of {@link Shop}
	 */
	public void save(Shop shop);

	/**
	 * Used to search the shop by name.
	 * 
	 * @param name
	 *            The searchKey for the name attribute.
	 * @return The list of matching shops
	 */

	public List<Shop> searchByName(String name);
}
