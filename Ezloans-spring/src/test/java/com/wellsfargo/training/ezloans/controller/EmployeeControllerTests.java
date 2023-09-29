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

import com.wellsfargo.training.ezloans.model.Employee;
import com.wellsfargo.training.ezloans.service.EmployeeService;



@DisplayName("Testing EmployeeCrontroller methods")
@SpringBootTest
public class EmployeeControllerTests {
	

	@Autowired
	private EmployeeController employeeController;
	
	private Employee e;
	
	@MockBean // create a mock object
	private EmployeeService eservice;
	
	@BeforeEach
	void setUp() throws Exception {
		e =new Employee();
	}

	@AfterEach
	void tearDown() throws Exception {
		e=null;
	}
	
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
	
}
