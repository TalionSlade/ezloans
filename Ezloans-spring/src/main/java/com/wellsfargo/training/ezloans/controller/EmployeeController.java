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
import com.wellsfargo.training.ezloans.service.EmployeeService;
@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping(value="/api")
public class EmployeeController {
	
	@Autowired
	public EmployeeService eservice;
	@PostMapping("/empreg")
	public ResponseEntity<String> createUser(@Validated @RequestBody Employee employee){
		try {
			
			Employee emp=eservice.registerEmployee(employee);
			if(emp!=null) {
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
	
	/*
	 * Sample json
	 * {
    "dob": "1980-10-02",
    "doj": "2023-11-02",
    "department": "DTI",
    "designation": "Program Associate",
    "gender": "Male",
    "fname": "Admin",
    "lname":"Sharma",
    "password":"admin",
    "email":"admin@ezloans.com"
}
	 */
	
	@PostMapping("/login")
	public boolean loginDealer(@Validated @RequestBody Employee employee) throws ResourceNotFoundException {
		
		Boolean isloggedin=false;
		
		String email=employee.getEmail();
		String password=employee.getPassword();
		
		Employee emp=eservice.loginEmployee(email).orElseThrow(()->new ResourceNotFoundException("Employee not found for this email"));
		
		if(email.equals(employee.getEmail())&&password.equals(emp.getPassword())){
			isloggedin=true;
		}
		
		return isloggedin;
		
	}
	
	/*
	 * {
  
    "password":"admin",
    "email":"admin@ezloans.com"
}
	 */

}

/*
 * 
 Sample POST request for http://localhost:8085/ezloans/api/empreg
 {
    "fname": "Jane",
    "lname": "Doe",
    "email": "jane@ezloans.com",
    "password":"password",
    "designation": "Program Associate",
    "department": "DTI",
    "gender": "Female",
    "dob": "2000-10-02",
    "doj": "2021-11-19"
}
 * */
