package com.wellsfargo.training.ezloans.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.training.ezloans.model.Employee;
import com.wellsfargo.training.ezloans.model.EmployeeCard;

import com.wellsfargo.training.ezloans.model.EmployeeIssue;
import com.wellsfargo.training.ezloans.model.LoanCard;
import com.wellsfargo.training.ezloans.service.EmployeeCardService;
import com.wellsfargo.training.ezloans.service.EmployeeService;
import com.wellsfargo.training.ezloans.service.LoanCardService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping(value="/api")
public class EmployeeCardController {
	
	@Autowired
	private EmployeeCardService ecardservice;
	
	@Autowired
	private EmployeeService  eservice;
	
	@Autowired
	private LoanCardService  lservice;
	
	@PostMapping("/addemployeecard")
	public ResponseEntity<String> addEmployeeCard(@Validated @RequestBody EmployeeCard empCard) {
    try {
			
			EmployeeCard empcardcreated=ecardservice.addEmployeeCard(empCard);
			if(empcardcreated!=null) {
				return ResponseEntity.ok("Employee Card added Successfully");
			}
			else
				return ResponseEntity.badRequest().body("Failed to add Employee Card");
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An Error has Occured: "+ e.getMessage());
		}
	}
	
	
	
	@GetMapping("/employeecards")
	public ResponseEntity<List<EmployeeCard>> getEmployeeCardsInfo(){
		try {
			List<EmployeeCard> employeecards=ecardservice.getEmployeeCards();
			return ResponseEntity.ok(employeecards);
					}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	@GetMapping("/employeecardsofemployee/{id}")
	public ResponseEntity<List<EmployeeCard>> getEmployeeCardsOfEmployee(@PathVariable(value="id") Long id){
	
		try {
			List<EmployeeCard>employeecards=ecardservice.getEmployeeCardsOfEmployee(id);
			return ResponseEntity.ok(employeecards);
		}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
			
		}
	}
	
	
	
}
