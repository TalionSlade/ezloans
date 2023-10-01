package com.wellsfargo.training.ezloans.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wellsfargo.training.ezloans.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
	//Optional used to deal with Null Pointer Exception
		//Custom method to find record by email non id field
	public Optional<Item> findByDesc(String desc);
	
	@Query(value = "SELECT ITEM_DESC,ITEM_VALUATION,ITEM_MAKE FROM ITEM_MASTER WHERE ITEM_CATEGORY=?1 AND ITEM_STATUS LIKE 'YES'", nativeQuery=true)
	public List<Object[]> getDescriptionByType(String type);
}

