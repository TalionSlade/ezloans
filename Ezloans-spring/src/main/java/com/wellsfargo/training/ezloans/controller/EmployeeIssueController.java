package com.wellsfargo.training.ezloans.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.training.ezloans.model.Employee;
import com.wellsfargo.training.ezloans.model.EmployeeCard;
import com.wellsfargo.training.ezloans.model.EmployeeIssue;
import com.wellsfargo.training.ezloans.model.Item;
import com.wellsfargo.training.ezloans.model.LoanCard;
import com.wellsfargo.training.ezloans.service.EmployeeCardService;
import com.wellsfargo.training.ezloans.service.EmployeeIssueService;
import com.wellsfargo.training.ezloans.service.EmployeeService;
import com.wellsfargo.training.ezloans.service.ItemService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping(value="/api")
public class EmployeeIssueController {

	@Autowired
	private EmployeeIssueService eissueservice;
	
	@Autowired
	private EmployeeService  eservice;
	
	@Autowired
	private ItemService iservice;
	
	@PostMapping("/addemployeeissue")
	public ResponseEntity<String> addEmployeeIssue(@Validated @RequestBody EmployeeIssue empIssue) {
    try {
			
			EmployeeIssue empissuecreated=eissueservice.addEmployeeIssue(empIssue);
			if(empissuecreated!=null) {
				return ResponseEntity.ok("Employee Issue added Successfully");
			}
			else
				return ResponseEntity.badRequest().body("Failed to add Employee Issue");
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An Error has Occured: "+ e.getMessage());
		}
	}
	
	@PostMapping("/addemployeeissuetest")
	public ResponseEntity<String> addEmployeeIssue() {
  
		Date current=new Date();
		Employee emp=new Employee().builder().department("HR").designation("ceo").dob((java.util.Date) current).doj((java.util.Date) current).email("anu@gmail.com").fname("anu").gender("male").lname("rajput").password("anu").build();
		
		Employee empsave=eservice.registerEmployee(emp);
		
		Item  item=new Item().builder().category("stationary").desc("table").make("yes").status("yes").valuation(10).build();
		
		Item newitem=iservice.addItem(item);
//				EmployeeCard empissue=new EmployeeCard().builder().employee(emp).loanId(loancard).issueDate((java.util.Date) current).build();
		EmployeeIssue empissue=new EmployeeIssue().builder().employee(emp).issueDate(current).item(newitem).returnDate(current).build();
		try {
			
				EmployeeIssue empcardcreated=eissueservice.addEmployeeIssue(empissue);
				if(empcardcreated!=null) {
					return ResponseEntity.ok("Employee Issue added Successfully");
				}
				else
					return ResponseEntity.badRequest().body("Failed to add Employee Issue");
			}
			catch(Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("An Error has Occured: "+ e.getMessage());
			
		
				}
	}
	
	@GetMapping("/employeeissues")
	public ResponseEntity<List<EmployeeIssue>> getEmployeeIssues(){
		try {
			List<EmployeeIssue> employeeissues=eissueservice.getEmployeeIssues();
			return ResponseEntity.ok(employeeissues);
					}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

}
