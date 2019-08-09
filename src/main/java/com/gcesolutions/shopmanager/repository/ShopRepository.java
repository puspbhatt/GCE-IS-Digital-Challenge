package com.gcesolutions.shopmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gcesolutions.shopmanager.model.Shop;

/**
 * 
 * @author Nand Joshi
 *
 */
@Repository
public interface ShopRepository extends CrudRepository<Shop, Integer> {
	@Query("select s from Shop s where s.name like %?1%")
	List<Shop> searchByName(@Param("name") String name);

}
