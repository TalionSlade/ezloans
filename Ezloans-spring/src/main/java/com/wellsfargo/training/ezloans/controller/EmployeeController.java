package com.wellsfargo.training.ezloans.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.training.ezloans.exception.ResourceNotFoundException;
import com.wellsfargo.training.ezloans.model.Employee;
import com.wellsfargo.training.ezloans.service.EmployeeService;



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
	
	@PostMapping("/login")
	public boolean loginDealer(@Validated @RequestBody Employee employee) throws ResourceNotFoundException {
		
		Boolean isloggedin=false;
		
		String employee_name=employee.getEname();
		String password=employee.getEmployee_password();
		
		Employee emp=eservice.loginEmployee(employee_name).orElseThrow(()->new ResourceNotFoundException("Employee not found for this email"));
		
		if(employee_name.equals(employee.getEname())&&password.equals(emp.getEmployee_password())){
			isloggedin=true;
		}
		
		return isloggedin;
		
	}

}
