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

import com.wellsfargo.training.ezloans.exception.ResourceNotFoundException;
import com.wellsfargo.training.ezloans.model.Employee;
import com.wellsfargo.training.ezloans.model.EmployeeIssue;
import com.wellsfargo.training.ezloans.model.Item;
import com.wellsfargo.training.ezloans.service.EmployeeIssueService;
import com.wellsfargo.training.ezloans.service.EmployeeService;
import com.wellsfargo.training.ezloans.service.ItemService;

import jakarta.persistence.EntityManager;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping(value="/api")
public class EmployeeIssueController {
	@Autowired
	private EmployeeIssueService eiservice;
	@Autowired
	public EmployeeService eservice;
	@Autowired
	public ItemService iservice;
	@PostMapping("/itemissue")
	public ResponseEntity<String> issueItems(@Validated @RequestBody EmployeeIssue ei){
		try {
			
			
			Employee emp=eservice.getEmployee(ei.getEmployee().getEid()).orElseThrow(()->new ResourceNotFoundException("Employee not found for this id"));			
			ei.setEmployee(emp);
			Item item=iservice.getItem(ei.getItem().getItemId()).orElseThrow(()->new ResourceNotFoundException("Item not found for this id"));			
			ei.setItem(item);
			EmployeeIssue empIs=eiservice.registerEmployeeIssue(ei);
			if(empIs!=null) {
				return ResponseEntity.ok("Registration Successfull");
			}
			else
				return ResponseEntity.badRequest().body("Registration Failed");
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An Error has Occured: "+ e.getMessage());
		}
		
		
	}
}


