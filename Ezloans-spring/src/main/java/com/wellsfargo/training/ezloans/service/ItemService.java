package com.wellsfargo.training.ezloans.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.training.ezloans.model.Employee;
import com.wellsfargo.training.ezloans.model.Item;
import com.wellsfargo.training.ezloans.repository.ItemRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ItemService {
	
	@Autowired
	public ItemRepository irepo;

	
	public Item registerItems(Item i) {
		return irepo.save(i);
	}
	public Optional<Item> getItem(Long id){
		return irepo.findById(id);
	}
	
	public Optional<Item> getItemByDesc(String desc){
		return irepo.findByDesc(desc);
	}
	
	

}

