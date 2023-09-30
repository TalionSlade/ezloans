package com.wellsfargo.training.ezloans.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
import com.wellsfargo.training.ezloans.model.LoanCard;
import com.wellsfargo.training.ezloans.service.EmployeeCardService;

@SpringBootTest
class EmployeeCardControllerTest {
	
	@Autowired
	private EmployeeCardController employeeCardController;
	
	private EmployeeCard  empCard;
	
	@MockBean  
	EmployeeCardService empCardService;

	@BeforeEach
	void setUp() throws Exception {
		empCard=new EmployeeCard();
	}

	@AfterEach
	void tearDown() throws Exception {
		empCard=null;
	}

	@Test
	void testaddEmployeeCard() throws ParseException {
		
		Employee e = new Employee();
	
		
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
	    empCard.setEmployee(e);;
	    empCard.setIssueDate(issue_date);
	  
	    
	    LoanCard lc=new LoanCard();
	    
	    
	 	    
	    lc.setType("Crockery");
	    lc.setDuration(10);
	
	    
	    empCard.setLoanId(lc);
	    when(empCardService.addEmployeeCard(any(EmployeeCard.class))).thenReturn(empCard);
	    
	    ResponseEntity<String> re=employeeCardController.addEmployeeCard(empCard);
		
		assertEquals(HttpStatus.OK, re.getStatusCode());
		assertEquals("Employee Card added Successfully", re.getBody());
		
		verify(empCardService, times(1)).addEmployeeCard(any(EmployeeCard.class));
		


	}
	
	
	@Test
	void testaddEmployeeCard_Else() throws ParseException {
		
		Employee e = new Employee();
	
		
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
	    empCard.setEmployee(e);;
	    empCard.setIssueDate(issue_date);
	  
	    
	    LoanCard lc=new LoanCard();
	    
	    
	 	    
	    lc.setType("Crockery");
	    lc.setDuration(10);
	
	    
	    empCard.setLoanId(lc);
	    when(empCardService.addEmployeeCard(any(EmployeeCard.class))).thenReturn(null);
	    
	    ResponseEntity<String> re=employeeCardController.addEmployeeCard(empCard);
		
		assertEquals(HttpStatus.BAD_REQUEST, re.getStatusCode());
		assertEquals("Failed to add Employee Card", re.getBody());
		
		verify(empCardService, times(1)).addEmployeeCard(any(EmployeeCard.class));
		


	}
	
	@Test
	void testaddEmployeeCard_Fail() throws ParseException {
		
		Employee e = new Employee();
	
		
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
	    empCard.setEmployee(e);;
	    empCard.setIssueDate(issue_date);
	  
	    
	    LoanCard lc=new LoanCard();
	    
	    
	 	    
	    lc.setType("Crockery");
	    lc.setDuration(10);
	
	    
	    empCard.setLoanId(lc);
	    Exception ex=new IllegalStateException();
	    when(empCardService.addEmployeeCard(any(EmployeeCard.class))).thenThrow(ex);
	    
	    ResponseEntity<String> re=employeeCardController.addEmployeeCard(empCard);
		
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, re.getStatusCode());
		assertEquals("An Error has Occured: "+ex.getMessage(), re.getBody());
		
		verify(empCardService, times(1)).addEmployeeCard(any(EmployeeCard.class));
		


	}
	
	@Test
	void testgetEmployeeCardsOfEmployee() throws ParseException {
		
		
	
		List<EmployeeCard>eclist=new ArrayList<EmployeeCard>();
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
	    empCard.setEmployee(e);;
	    empCard.setIssueDate(issue_date);
	  
	    
	    LoanCard lc=new LoanCard();
	    
	    
	 	    
	    lc.setType("Crockery");
	    lc.setDuration(10);
	
	    
	    empCard.setLoanId(lc);
	 
	    eclist.add(empCard);
	    
	    
	    
	    
	    EmployeeCard ec2=new EmployeeCard();
	    Employee e2 = new Employee();
	    e2.setEid(2L);
	    e2.setEmail("anubhav@example.com");
	    e2.setFname("anubhav");
	    e2.setLname("rajput");
	    e2.setGender("male");
	    e2.setDepartment("CT");
	    e2.setDesignation("Program Associate");
	    
	    
	    Date dob2=new Date(df.parse("1985-01-01").getTime());
	    e2.setDob(dob);
	    Date doj2=new Date(df.parse("2023-08-23").getTime());
	    e2.setDoj(doj);
	    e2.setPassword("password");
	    
	    
	    Date issue_date2=new Date(df2.parse("2023-09-20").getTime());
	    ec2.setEmployee(e2);;
	    ec2.setIssueDate(issue_date2);
//	    ec2.setCardId(2L);
	  
	    
	    LoanCard lc2=new LoanCard();
	    
	    
	 	    
	    lc2.setType("Crockery");
	    lc2.setDuration(10);
	    ec2.setLoanId(lc2);
	    
	    eclist.add(ec2);
	    
		List<EmployeeCard>returnclist=new ArrayList<EmployeeCard>();
		eclist.add(empCard);
	    
	    
	    when(empCardService.getEmployeeCardsOfEmployee(1L)).thenReturn(returnclist);
	    
	    ResponseEntity<List<EmployeeCard>> re=employeeCardController.getEmployeeCardsOfEmployee(1L);
		
		assertEquals(HttpStatus.OK, re.getStatusCode());
		assertEquals(returnclist, re.getBody());
		
		verify(empCardService, times(1)).getEmployeeCardsOfEmployee(1L);
		


	}
	
	@Test
	void testgetEmployeeCardsOfEmployee_Else() throws ParseException {
		
		
	
		List<EmployeeCard>eclist=new ArrayList<EmployeeCard>();
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
	    empCard.setEmployee(e);;
	    empCard.setIssueDate(issue_date);
	  
	    
	    LoanCard lc=new LoanCard();
	    
	    
	 	    
	    lc.setType("Crockery");
	    lc.setDuration(10);
	
	    
	    empCard.setLoanId(lc);
	 
	    eclist.add(empCard);
	    
	    
	    
	    
	    EmployeeCard ec2=new EmployeeCard();
	    Employee e2 = new Employee();
	    e2.setEid(2L);
	    e2.setEmail("anubhav@example.com");
	    e2.setFname("anubhav");
	    e2.setLname("rajput");
	    e2.setGender("male");
	    e2.setDepartment("CT");
	    e2.setDesignation("Program Associate");
	    
	    
	    Date dob2=new Date(df.parse("1985-01-01").getTime());
	    e2.setDob(dob);
	    Date doj2=new Date(df.parse("2023-08-23").getTime());
	    e2.setDoj(doj);
	    e2.setPassword("password");
	    
	    
	    Date issue_date2=new Date(df2.parse("2023-09-20").getTime());
	    ec2.setEmployee(e2);;
	    ec2.setIssueDate(issue_date2);
//	    ec2.setCardId(2L);
	  
	    
	    LoanCard lc2=new LoanCard();
	    
	    
	 	    
	    lc2.setType("Crockery");
	    lc2.setDuration(10);
	    ec2.setLoanId(lc2);
	    
	    eclist.add(ec2);
	    
		List<EmployeeCard>returnclist=new ArrayList<EmployeeCard>();
		eclist.add(empCard);
	    
	    Exception ex=new IllegalStateException();
	    when(empCardService.getEmployeeCardsOfEmployee(1L)).thenThrow(ex);
	    
	    ResponseEntity<List<EmployeeCard>> re=employeeCardController.getEmployeeCardsOfEmployee(1L);
		
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, re.getStatusCode());
		assertNull(re.getBody());
		
		verify(empCardService, times(1)).getEmployeeCardsOfEmployee(1L);
		


	}


}
