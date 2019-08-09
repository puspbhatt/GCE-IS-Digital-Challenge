package com.gcesolutions.shopmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcesolutions.shopmanager.model.Shop;
import com.gcesolutions.shopmanager.repository.ShopRepository;

/**
 * 
 * @author Nand Joshi
 *
 */
@Service("shopService")
public class ShopServiceImpl implements ShopService {
	@Autowired
	private ShopRepository shopRepository;

	@Override
	public List<Shop> findAll() {

		return (List<Shop>) shopRepository.findAll();
	}

	@Override
	public void save(Shop shop) {
		shopRepository.save(shop);

	}

	@Override
	public List<Shop> searchByName(String name) {
		return (List<Shop>) shopRepository.searchByName(name);
	}

}
