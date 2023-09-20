package com.wellsfargo.training.ezloans.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.training.ezloans.model.Item;
import com.wellsfargo.training.ezloans.model.LoanCard;
import com.wellsfargo.training.ezloans.service.ItemService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping(value="/api")
public class ItemController {
	
	@Autowired
	private ItemService iService;
	
	@PostMapping("/additem")
	public ResponseEntity<String> addItem(@Validated @RequestBody Item item) {
    try {
			
			Item newItemCreated=iService.addItem(item);
			if(item!=null) {
				return ResponseEntity.ok("Item added Successfully");
			}
			else
				return ResponseEntity.badRequest().body("Failed to Item");
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An Error has Occured: "+ e.getMessage());
		}
	}

}
