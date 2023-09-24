package com.wellsfargo.training.ezloans.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wellsfargo.training.ezloans.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
		
	public Optional<Item> findByDesc(String desc);
	
	@Query(value = "SELECT ITEM_DESC,ITEM_VALUATION FROM ITEM_MASTER WHERE ITEM_CATEGORY=?1", nativeQuery=true)
	public List<Object[]> getDescriptionByType(String type);
}

