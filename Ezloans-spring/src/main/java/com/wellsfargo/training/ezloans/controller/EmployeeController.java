package com.wellsfargo.training.ezloans.controller;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

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
	
	@Autowired
	public EmployeeService eservice;
	
	@Autowired
	public EmployeeCardService ecardservice;
	
	@Autowired
	public ItemService iservice;
	
	@Autowired
	public EmployeeIssueService eissueservice;
	
	
	@Autowired
	public LoanCardService lcardservice;
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
    "dob": "02/10/1980",
    "doj": "02/11/1983",
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
	public boolean loginEmployee(@Validated @RequestBody Employee employee) throws ResourceNotFoundException {
		
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
	
	@PostMapping("/applyloan")
	public ResponseEntity<String> applyLoan(@Validated @RequestBody Map<String, String> requestMap) throws ResourceNotFoundException {
		
		Long eid=Long.parseLong(requestMap.get("eid"));
		String itemCategory=requestMap.get("category");
		String itemDescription=requestMap.get("description");
		Long itemValue=Long.parseLong(requestMap.get("value"));
		String itemMake=requestMap.get("make");
		Date issueDate=new Date();
		
		
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
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An Error has Occured: "+ e.getMessage());
		}
		

		
		
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
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value="id") long eid)throws ResourceNotFoundException{
		Employee e=eservice.getEmployee(eid).orElseThrow(()->new ResourceNotFoundException("LoanCard not found for this id"+eid));
		return ResponseEntity.ok().body(e);
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
 * */
