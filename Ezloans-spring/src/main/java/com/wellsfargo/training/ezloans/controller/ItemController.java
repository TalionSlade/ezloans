package com.wellsfargo.training.ezloans.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.training.ezloans.model.Item;
import com.wellsfargo.training.ezloans.service.ItemService;

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

}

