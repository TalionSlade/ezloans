package com.wellsfargo.training.ezloans.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.wellsfargo.training.ezloans.model.Employee;
import com.wellsfargo.training.ezloans.model.EmployeeCard;
import com.wellsfargo.training.ezloans.model.EmployeeIssue;
import com.wellsfargo.training.ezloans.model.Item;
import com.wellsfargo.training.ezloans.model.LoanCard;
import com.wellsfargo.training.ezloans.service.EmployeeCardService;
import com.wellsfargo.training.ezloans.service.EmployeeIssueService;

@SpringBootTest
public class EmployeeIssueTests {
	
	
	@Autowired
	private EmployeeIssueController employeeIssueController;
	
	private EmployeeIssue  empIssue;
	
	@MockBean  
	EmployeeIssueService empIssueService;

	@BeforeEach
	void setUp() throws Exception {
		empIssue=new EmployeeIssue();
	}

	@AfterEach
	void tearDown() throws Exception {
		empIssue=null;
	}
	
	
	@Test
	public void getEmployeeCardOfEmployeeTest() throws ParseException {
		
		List<EmployeeIssue>eilist=new ArrayList<>();
		Employee e = new Employee();
		e.setEid(1L);
		e.setEmail("arpan@example.com");
	    e.setFname("arpan");
	    e.setLname("ghosh");
	    e.setGender("male");
	    e.setDepartment("CT");
	    e.setDesignation("Program Associate");
	    
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    Date dob=new Date(df.parse("1985-01-01").getTime());
	    e.setDob(dob);
	    Date doj=new Date(df.parse("2023-08-23").getTime());
	    e.setDoj(doj);
	    e.setPassword("password");
	    
	    SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
	    Date issue_date=new Date(df2.parse("2023-09-20").getTime());
	    empIssue.setEmployee(e);;
	    empIssue.setIssueDate(issue_date);
	    empIssue.setReturnDate(issue_date);
	  
	    Item i=new Item();
	    
	    i.setDesc("Soundbar");
		i.setStatus("New");
		i.setMake("Wooden");
		i.setValuation(5000);
		i.setCategory("Appliance");
	    
	    empIssue.setItem(i);
	    
	    eilist.add(empIssue);
	 
	    
	    
	    
	   
	    when(empIssueService.getEmployeeIssuesOfEmployee(1L)).thenReturn(eilist);
	    
	    ResponseEntity<List<EmployeeIssue>> re=employeeIssueController.getEmployeeCardsOfEmployee(1L);
		
		assertEquals(HttpStatus.OK, re.getStatusCode());
		assertEquals(eilist, re.getBody());
		
		verify(empIssueService, times(1)).getEmployeeIssuesOfEmployee(1L);
		
	}
	
	

	@Test
	public void getEmployeeCardOfEmployeeTest_Fail() throws ParseException {
		
		List<EmployeeIssue>eilist=new ArrayList<>();
		Employee e = new Employee();
		e.setEid(1L);
		e.setEmail("arpan@example.com");
	    e.setFname("arpan");
	    e.setLname("ghosh");
	    e.setGender("male");
	    e.setDepartment("CT");
	    e.setDesignation("Program Associate");
	    
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    Date dob=new Date(df.parse("1985-01-01").getTime());
	    e.setDob(dob);
	    Date doj=new Date(df.parse("2023-08-23").getTime());
	    e.setDoj(doj);
	    e.setPassword("password");
	    
	    SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
	    Date issue_date=new Date(df2.parse("2023-09-20").getTime());
	    empIssue.setEmployee(e);;
	    empIssue.setIssueDate(issue_date);
	    empIssue.setReturnDate(issue_date);
	  
	    Item i=new Item();
	    
	    i.setDesc("Soundbar");
		i.setStatus("New");
		i.setMake("Wooden");
		i.setValuation(5000);
		i.setCategory("Appliance");
	    
	    empIssue.setItem(i);
	    
	    eilist.add(empIssue);
	 
	    
	    
	    
	   Exception ex=new IllegalStateException();
	   when(empIssueService.getEmployeeIssuesOfEmployee(1L)).thenThrow(ex);
	    
	    ResponseEntity<List<EmployeeIssue>> re=employeeIssueController.getEmployeeCardsOfEmployee(1L);
		
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, re.getStatusCode());
		assertNull(re.getBody());
		
		verify(empIssueService, times(1)).getEmployeeIssuesOfEmployee(1L);
		
	}

}
