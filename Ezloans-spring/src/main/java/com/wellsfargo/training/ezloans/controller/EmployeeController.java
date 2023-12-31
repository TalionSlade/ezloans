package com.wellsfargo.training.ezloans.controller;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.*;
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
import com.wellsfargo.training.ezloans.model.Employee;
import com.wellsfargo.training.ezloans.model.EmployeeCard;
import com.wellsfargo.training.ezloans.model.EmployeeIssue;
import com.wellsfargo.training.ezloans.model.Item;
import com.wellsfargo.training.ezloans.model.LoanCard;
import com.wellsfargo.training.ezloans.service.EmployeeCardService;
import com.wellsfargo.training.ezloans.service.EmployeeIssueService;
import com.wellsfargo.training.ezloans.service.EmployeeService;
import com.wellsfargo.training.ezloans.service.ItemService;
import com.wellsfargo.training.ezloans.service.LoanCardService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping(value="/api")
public class EmployeeController {
	/* This class is designed to handle all request to the Employee Service */
	@Autowired
	public EmployeeService eservice;

	@Autowired
	public EmployeeCardService ecardservice;
	
	@Autowired
	public ItemService iservice;
	
	@Autowired
	public EmployeeIssueService eissueservice;
	/* We use dependency injection to created required service class objects*/
	
	@Autowired
	public LoanCardService lcardservice;
	
	/* Employee Register - this method is used to createUser and save in the database*/
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
	

// http://localhost:8085/ezloans/api/login
	/*
	 * {
  
    "password":"admin",
    "email":"admin@ezloans.com"
}
	 */
	//This function enable the login functionality
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> loginEmployee(@Validated @RequestBody Employee employee) throws ResourceNotFoundException {
		
		Boolean isloggedin=false;
		
		String email=employee.getEmail();
		String password=employee.getPassword();
		
		//We fetch an existing employee up with the given email id
		Employee emp=eservice.loginEmployee(email).orElseThrow(()->new ResourceNotFoundException("Employee not found for this email"));
		//we compare the employee email and the password
		if(email.equals(employee.getEmail())&&password.equals(emp.getPassword())){
			isloggedin=true;
			
		}

		Map<String,Object> response = new HashMap<String, Object>();
		response.put("isloggedin",isloggedin);
		response.put("eid",emp.getEid());
		return ResponseEntity.ok(response);
		
		//return a custom JSON response to the frontend service for login
		
	}
	

	/*
	 * {
    "eid":103,
    "category":"Stationary",
    "description":"Register",
    "value":10,
    "make":"yes"
}
	 */
	
	/*This is the  apply loan functionality for the employee class*/
	@PostMapping("/applyloan")
	public ResponseEntity<String> applyLoan(@Validated @RequestBody Map<String, String> requestMap) throws ResourceNotFoundException {
		
		Long eid=Long.parseLong(requestMap.get("eid"));
		String itemCategory=requestMap.get("category");
		String itemDescription=requestMap.get("description");
		Long itemValue=Long.parseLong(requestMap.get("value"));
		String itemMake=requestMap.get("make");
		Date issueDate=new Date();
		
		/*In this try block , we fetch an employee id , a load card obj , set the duration , create an EmployeeCard and push it in database*/
		try {
			Employee employeeobj=eservice.getEmployee(eid).orElseThrow(()->new ResourceNotFoundException("employee id not present"));
			
			LoanCard loancardobj=lcardservice.getLoanCardByType(itemCategory).orElseThrow(()->new ResourceNotFoundException("Loan Card could not be found by type"));
			
			int duration=loancardobj.getDuration();
			
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(issueDate);
			
			calendar.add(Calendar.DAY_OF_YEAR, duration);
			
			Date returnDate=calendar.getTime();			
			EmployeeCard empcard=EmployeeCard.builder().employee(employeeobj).loanId(loancardobj).issueDate(issueDate).build();
			
			EmployeeCard empcardcreated=ecardservice.addEmployeeCard(empcard);
			
			Item item=iservice.getItemByDesc(itemDescription).orElseThrow(()->new ResourceNotFoundException("Item not found by description"));
			
			
			EmployeeIssue empissue=EmployeeIssue.builder().employee(employeeobj).issueDate(issueDate).returnDate(returnDate).item(item).build();
			
			EmployeeIssue empissuecreated=eissueservice.registerEmployeeIssue(empissue);
			if(empissuecreated!=null) {
					return ResponseEntity.ok("Loan Successfully applied");
			}
			else {
				
					return ResponseEntity.badRequest().body("Loan not applied successfully");
			}
		}
		catch(Exception e) {
			
			//On failure we raise an exception and set the response entity status to Internal Server Error
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An Error has Occured: "+ e.getMessage());
		}
		

		
		
	}



	// GET: http://localhost:8085/ezloans/api/employee
	/*This is  a simple get mapping function to pull all employee records from the database */
	@GetMapping("/employee")
	public ResponseEntity< List<Employee>> getAllEmployees() {
		
		try {
			List<Employee> employees =  eservice.listAll();
			return ResponseEntity.ok(employees);
			} catch(Exception e) {
				//On  failure we raise exception and print the stack trace
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
				}
	}
	
	// GET: http://localhost:8085/ezloans/api/employee/1001
	
	// This function pulls Employee records based on matching Employee ID
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getEmployeesById(@PathVariable(value="id") long eid) 
			throws ResourceNotFoundException{
		Employee e=eservice.getSingleEmployee(eid).orElseThrow(()->new ResourceNotFoundException("Employee not found for this id "+eid));
		return ResponseEntity.ok().body(e);
	}
	
	// PUT: http://localhost:8085/ezloans/api/employee/1001
	
	//This function is used to update records of an given employee , specified by there ID.
	@PutMapping("/employee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value="id") long eid, @Validated @RequestBody Employee e) 
			throws ResourceNotFoundException{
		Employee employee=eservice.getSingleEmployee(eid).orElseThrow(()->new ResourceNotFoundException("Employee not found for this id "+eid));
		
		employee.setFname(e.getFname());
		employee.setLname(e.getLname());
		employee.setEmail(e.getEmail());
		employee.setGender(e.getGender());
		employee.setDesignation(e.getDesignation());
		employee.setDepartment(e.getDepartment());
		employee.setDob(e.getDob());
		employee.setDoj(e.getDoj());
		
		final Employee updatedEmployee = eservice.saveEmployee(employee);
		return ResponseEntity.ok().body(updatedEmployee);
		
	}
	
	// http://localhost:8085/ezloans/api/employee/1001
	
	//This function is used to delete a specific employee record , identified by the given Employee ID
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<Map<String, Boolean>>  deleteEmployee(@PathVariable(value="id") long eid)
	throws ResourceNotFoundException {
		
		eservice.getSingleEmployee(eid).orElseThrow(()->new ResourceNotFoundException("Employee not found for this id "+eid));
		eservice.deleteEmployee(eid);
		
		Map<String,Boolean> response = new HashMap<String, Boolean>();
		response.put("Deleted",Boolean.TRUE);
		return ResponseEntity.ok(response);		
	}

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

Sample POST for http://localhost:8085/ezloans/api/login

{
    "password":"admin",
    "email":"admin@ezloans.com"
}
	 
 * */
