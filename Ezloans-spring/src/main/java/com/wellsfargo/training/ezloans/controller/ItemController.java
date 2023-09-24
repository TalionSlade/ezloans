package com.wellsfargo.training.ezloans.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.training.ezloans.exception.ResourceNotFoundException;
import com.wellsfargo.training.ezloans.model.Item;
import com.wellsfargo.training.ezloans.model.Item;
import com.wellsfargo.training.ezloans.service.ItemService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping(value = "/api")
public class ItemController {
	
	@Autowired
	public ItemService iservice;
	
	@PostMapping("/additem")
	public ResponseEntity<String> insertItem(@Validated @RequestBody Item item){
		try {
			
			Item registeredItem = iservice.registerItems(item);
			if(registeredItem != null) {
				return ResponseEntity.ok("GG!! Successfull");
			}else {
				return ResponseEntity.badRequest().body("NT!  Registration failed!!");
			}
			
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Occured :- "+ e.getMessage());
		}
	}
	
	// GET: http://localhost:8085/ezloans/api/item
	@GetMapping("/item")
	public ResponseEntity<List<Item>> getAllItems() {
		
		try {
			List<Item> items =  iservice.listAll();
			return ResponseEntity.ok(items);
			} catch(Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
				}
	}
	
	@PostMapping("/item")
	public ResponseEntity<Item> saveProduct(@Validated @RequestBody Item item) {
		try {
		Item i=iservice.saveItem(item);
		return ResponseEntity.status(HttpStatus.CREATED).body(i);
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
			}
	}
		
	// GET: http://localhost:8085/ezloans/api/item/1001
	@GetMapping("/item/{id}")
	public ResponseEntity<Item> getItemsById(@PathVariable(value="id") long id) 
			throws ResourceNotFoundException{
		Item i=iservice.getSingleItem(id).orElseThrow(()->new ResourceNotFoundException("Item not found for this id "+id));
		return ResponseEntity.ok().body(i);
	}

	// http://localhost:8085/ezloans/api/item/1001
	@DeleteMapping("/item/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteItem(@PathVariable(value="id") long id)
	throws ResourceNotFoundException {
		
		iservice.getSingleItem(id).orElseThrow(()->new ResourceNotFoundException("Item not found for this id "+id));
		iservice.deleteItem(id);
		
		Map<String,Boolean> response = new HashMap<String, Boolean>();
		response.put("Deleted Item",Boolean.TRUE);
		return ResponseEntity.ok(response);		
	}
	
	// PUT: http://localhost:8085/ezloans/api/item/1001
	@PutMapping("/item/{id}")
	public ResponseEntity<Item> updateItem(@PathVariable(value="id") long id, @Validated @RequestBody Item i) 
			throws ResourceNotFoundException{
		Item item=iservice.getSingleItem(id).orElseThrow(()->new ResourceNotFoundException("Item not found for this id "+id));
		
		item.setDesc(i.getDesc());
		item.setStatus(i.getStatus());
		item.setMake(i.getMake());
		item.setCategory(i.getCategory());
		item.setValuation(i.getValuation());
		
		final Item updatedItem = iservice.saveItem(item);
		return ResponseEntity.ok().body(updatedItem);
		
	}
	
	
	/*
	 * {
    "desc":"Spoon",
    "make":"wooden",
    "category":"Crockery",
    "valuation":100,
    "status":"Yes"
}
	 */
}

