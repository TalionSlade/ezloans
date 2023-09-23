package com.wellsfargo.training.ezloans.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wellsfargo.training.ezloans.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
		
	public Optional<Item> findByDesc(String desc);
}

