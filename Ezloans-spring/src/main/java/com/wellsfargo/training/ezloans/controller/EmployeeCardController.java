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
		/* This class is used to design controller from the employee card service*/
		
	/*We use dependency injection to initialize the service class objects here..*/
	@Autowired
	private EmployeeCardService ecardservice;
	
	@Autowired
	private EmployeeService  eservice;
	
	@Autowired
	private LoanCardService  lservice;
	
	
	/*This fucntion is called when a new employee card object is to be created and sent through POST service to the calling service*/
	@PostMapping("/addemployeecard")
	public ResponseEntity<String> addEmployeeCard(@Validated @RequestBody EmployeeCard empCard) {
    try {
			
			EmployeeCard empcardcreated=ecardservice.addEmployeeCard(empCard);
			if(empcardcreated!=null) {
				// set Response Entity to OK if the card is succesfully added
				return ResponseEntity.ok("Employee Card added Successfully");
			}
			else
				//set RespenseEntity to badRequest if card cannot be added
				return ResponseEntity.badRequest().body("Failed to add Employee Card");
		}
		catch(Exception e) {
			// raise an Exception on failure 
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An Error has Occured: "+ e.getMessage());
		}
	}
	
	
	/* This function is used to retrieve the all the employee cards in the database through a get request*/
	@GetMapping("/employeecards")
	public ResponseEntity<List<EmployeeCard>> getEmployeeCardsInfo(){
		try {
			//we call the .getEmployeeCards function of employee card service and store the  employee cards in  an list
			List<EmployeeCard> employeecards=ecardservice.getEmployeeCards();
			//and return the ResponseEntity as status OK
			return ResponseEntity.ok(employeecards);
					}
		catch(Exception e) {
			e.printStackTrace();
			// Raise exception and  return Response entity with Internal Server Error 
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	
	/* This function is used to retrieve Employee Cards by a particular ID*/
	@GetMapping("/employeecard/{id}")
	public ResponseEntity<List<EmployeeCard>> getEmployeeCardsOfEmployee(@PathVariable(value="id") Long id){
	
		try {
			/*We created a list of Employee Card that belong to the employee of the said ID*/
			List<EmployeeCard>employeecards=ecardservice.getEmployeeCardsOfEmployee(id);
			return ResponseEntity.ok(employeecards);
		}
		catch(Exception e){
			//raise an exception on failure and set Response Entity Status to INTERNAL SERVER ERROR
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
			
		}
	}
	
	
	
}
