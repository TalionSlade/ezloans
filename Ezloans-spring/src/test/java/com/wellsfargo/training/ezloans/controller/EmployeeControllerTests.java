package com.wellsfargo.training.ezloans.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.wellsfargo.training.ezloans.exception.ResourceNotFoundException;
import com.wellsfargo.training.ezloans.model.Employee;
import com.wellsfargo.training.ezloans.model.EmployeeCard;
import com.wellsfargo.training.ezloans.model.LoanCard;
import com.wellsfargo.training.ezloans.service.EmployeeService;



@DisplayName("Testing EmployeeCrontroller methods")
@SpringBootTest
public class EmployeeControllerTests {
	/*@SpringBootTest annotation loads whole application, but it is better 
	 * to limit Application Context only to a set of spring components that 
	 * participate in test scenario.*/

	@Autowired
	private EmployeeController employeeController;
	
	private Employee e;
	
	@MockBean // allows us to mock a class or an interface and record & verify its behaviors.
	private EmployeeService eservice;
	
	@BeforeEach
	void setUp() throws Exception {
		e =new Employee();
	}

	@AfterEach
	void tearDown() throws Exception {
		e=null;
	}
	
	
	/*
	 *  We test our createUser functionality in this method.
	 *  We create a mock employee object and test the registerEmployee service and createUser controller function*/
	
	@Test
	void testCreateUser() throws ParseException {
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
	    
		//mockito methods
		//enables stubbing methods
		when(eservice.registerEmployee(any(Employee.class))).thenReturn(e);
		
		ResponseEntity<String> re=employeeController.createUser(e);
		
		assertEquals(HttpStatus.OK, re.getStatusCode());
		assertEquals("Registration Successfull", re.getBody());
		
		verify(eservice, times(1)).registerEmployee(any(Employee.class));
		
	}
	
	@Test
	void testCreateUser_ElseCase() throws ParseException {
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
	    
		//mockito methods
		//enables stubbing methods
		when(eservice.registerEmployee(any(Employee.class))).thenReturn(null);
		
		ResponseEntity<String> re=employeeController.createUser(e);
		
		assertEquals(HttpStatus.BAD_REQUEST, re.getStatusCode());
		assertEquals("Registration Failed", re.getBody());
		
		verify(eservice, times(1)).registerEmployee(any(Employee.class));
		
	}
	
	@Test
	void testCreateUser_FailCase() throws ParseException {
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
	    
		//mockito methods
		//enables stubbing methods
	    
	    Exception ex=new IllegalStateException();
		when(eservice.registerEmployee(any(Employee.class))).thenThrow(ex);
		
		ResponseEntity<String> re=employeeController.createUser(e);
		
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, re.getStatusCode());
		assertEquals("An Error has Occured: "+ex.getMessage(), re.getBody());
		
		verify(eservice, times(1)).registerEmployee(any(Employee.class));
		
	}
	
	//This function is used to get employee by an id function.
	@Test
	void testEmployeeById() throws ParseException, ResourceNotFoundException {
		
		
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
	    
	   
	    
	    when(eservice.getSingleEmployee(1L)).thenReturn(Optional.of(e));
	    
	    ResponseEntity<Employee> re = employeeController.getEmployeesById(1L);
	    
	    assertEquals(HttpStatus.OK, re.getStatusCode());
	    assertEquals("arpan", re.getBody().getFname());
	    assertEquals("ghosh", re.getBody().getLname());
	    assertEquals("arpan@example.com", re.getBody().getEmail());
	    assertEquals("Program Associate", re.getBody().getDesignation());
	    
	    verify(eservice, times(1)).getSingleEmployee(1L);
	    
	    
	}
	
	
	//This function is used to update employee function.
		@Test
		void testUpdateEmployee() throws ParseException, ResourceNotFoundException {
			
		
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
		    
		   
		    Employee e2=new Employee();
		    
	;
		    e2.setEmail("arpanghosh@example.com");
		    e2.setFname("arpan");
		    e2.setLname("ghosh");
		    e2.setGender("male");
		    e2.setDepartment("CT");
		    e2.setDesignation("Program Associate");
	
		    e2.setDob(dob);

		    e2.setDoj(doj);
		    e2.setPassword("password");
			when(eservice.getSingleEmployee(1L)).thenReturn(Optional.of(e));
			when(eservice.saveEmployee(any(Employee.class))).thenReturn(e2);
			
			ResponseEntity<Employee> re = employeeController.updateEmployee(1L, e2);
			
			  
		    assertEquals(HttpStatus.OK, re.getStatusCode());
		    assertEquals("arpan", re.getBody().getFname());
		    assertEquals("ghosh", re.getBody().getLname());
		    assertEquals("arpanghosh@example.com", re.getBody().getEmail());
		    assertEquals("Program Associate", re.getBody().getDesignation());
			
			verify(eservice, times(1)).getSingleEmployee(1L);
			verify(eservice, times(1)).saveEmployee(any(Employee.class));
		    
		    
		    
		}
		
	
}
