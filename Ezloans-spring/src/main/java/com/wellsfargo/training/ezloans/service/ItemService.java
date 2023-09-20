package com.wellsfargo.training.ezloans.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.training.ezloans.model.Item;
import com.wellsfargo.training.ezloans.repository.ItemRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ItemService {
	
	@Autowired
	private ItemRepository irepo;
	
	public Item addItem(Item item) {
		
		return irepo.save(item);
		
	}

}
