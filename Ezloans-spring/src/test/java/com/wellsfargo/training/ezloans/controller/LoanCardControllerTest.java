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
import com.wellsfargo.training.ezloans.service.LoanCardService;

@DisplayName("Loan Card Controller tests")
@SpringBootTest
class LoanCardControllerTest {
	
	@Autowired
	private LoanCardController loanCardController;
	
	LoanCard lc;
	
	@MockBean
	private LoanCardService lservice;
	
	@BeforeEach
	void setUp() throws Exception {
		lc = new LoanCard();
	}

	@AfterEach
	void tearDown() throws Exception {
		lc = null;
	}

	@Test
	void testAddLoanCard() throws ParseException {
		lc.setType("Crockery");
		lc.setDuration(5);
		
		EmployeeCard ec = new EmployeeCard();
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
	    
	    ec.setEmployee(e);
	    ec.setLoanId(lc);
	    ec.setIssueDate(doj);
	    
	    List<EmployeeCard> ecs = new ArrayList<EmployeeCard>();
	    ecs.add(ec);
	    
	    lc.setEmployeeCard(ecs);
	    
	    when(lservice.addLoanCard(any(LoanCard.class))).thenReturn(lc);
		
		ResponseEntity<String> re=loanCardController.addLoanCard(lc);
		
		assertEquals(HttpStatus.OK, re.getStatusCode());
		assertEquals("Loan Card added Successfully", re.getBody());
		
		verify(lservice, times(1)).addLoanCard(any(LoanCard.class));
		
	}
	
	@Test
	void testAddLoanCard_Else() throws ParseException {
		lc.setType("Crockery");
		lc.setDuration(5);
		
		EmployeeCard ec = new EmployeeCard();
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
	    
	    ec.setEmployee(e);
	    ec.setLoanId(lc);
	    ec.setIssueDate(doj);
	    
	    List<EmployeeCard> ecs = new ArrayList<EmployeeCard>();
	    ecs.add(ec);
	    
	    lc.setEmployeeCard(ecs);
	    when(lservice.addLoanCard(any(LoanCard.class))).thenReturn(null);
	    
	    ResponseEntity<String> re=loanCardController.addLoanCard(lc);
		
		assertEquals(HttpStatus.BAD_REQUEST, re.getStatusCode());
		assertEquals("Failed to add Loan Card", re.getBody());
		
		verify(lservice, times(1)).addLoanCard(any(LoanCard.class));

	}
	
	@Test
	void testAddLoanCard_fail() throws ParseException {
		lc.setType("Crockery");
		lc.setDuration(5);
		
		EmployeeCard ec = new EmployeeCard();
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
	    
	    ec.setEmployee(e);
	    ec.setLoanId(lc);
	    ec.setIssueDate(doj);
	    
	    List<EmployeeCard> ecs = new ArrayList<EmployeeCard>();
	    ecs.add(ec);
	    
	    lc.setEmployeeCard(ecs);
	    Exception ex = new IllegalStateException();
	    when(lservice.addLoanCard(any(LoanCard.class))).thenThrow(ex);
	    
	    ResponseEntity<String> re=loanCardController.addLoanCard(lc);
	    
	    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, re.getStatusCode());
	    assertEquals("An Error has Occured: "+ex.getMessage(), re.getBody());
	    
	    verify(lservice, times(1)).addLoanCard(any(LoanCard.class));
	}

	@Test
	void testGetAllloancards() throws ParseException {
		LoanCard lc1 = new LoanCard();
		LoanCard lc2 = new LoanCard();
		
		lc1.setType("Crockery");
		lc1.setDuration(5);
		
		EmployeeCard ec1 = new EmployeeCard();
		Employee e1 = new Employee();
		
		e1.setEmail("arpan@example.com");
	    e1.setFname("arpan");
	    e1.setLname("ghosh");
	    e1.setGender("male");
	    e1.setDepartment("CT");
	    e1.setDesignation("Program Associate");
	    
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    Date dob=new Date(df.parse("1985-01-01").getTime());
	    e1.setDob(dob);
	    Date doj=new Date(df.parse("2023-08-23").getTime());
	    e1.setDoj(doj);
	    e1.setPassword("password");
	    
	    ec1.setEmployee(e1);
	    ec1.setLoanId(lc1);
	    ec1.setIssueDate(doj);
	    
	    List<EmployeeCard> ecs1 = new ArrayList<EmployeeCard>();
	    ecs1.add(ec1);
	    
	    lc1.setEmployeeCard(ecs1);
	    
	    lc2.setType("Furniture");
		lc2.setDuration(3);
		
		EmployeeCard ec2 = new EmployeeCard();
		Employee e2 = new Employee();
		
		e2.setEmail("anubhav@example.com");
	    e2.setFname("anubhav");
	    e2.setLname("rajput");
	    e2.setGender("male");
	    e2.setDepartment("TCOO");
	    e2.setDesignation("Program Associate");
	    
	    SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
	    Date dob2=new Date(df2.parse("2001-04-18").getTime());
	    e2.setDob(dob2);
	    Date doj2=new Date(df2.parse("2023-07-21").getTime());
	    e2.setDoj(doj2);
	    e2.setPassword("password");
	    
	    ec2.setEmployee(e2);
	    ec2.setLoanId(lc2);
	    ec2.setIssueDate(doj);
	    
	    List<EmployeeCard> ecs2 = new ArrayList<EmployeeCard>();
	    ecs2.add(ec2);
	    
	    lc2.setEmployeeCard(ecs2);
	    
	    List<LoanCard> list = new ArrayList<LoanCard>();
	    list.add(lc1);
	    list.add(lc2);
	    
	    when(lservice.listAll()).thenReturn(list);
	    
	    ResponseEntity<List<LoanCard>> re = loanCardController.getAllloancards();
	    
	    assertEquals(2, re.getBody().size());
	    assertEquals("Crockery", re.getBody().get(0).getType());
	    assertEquals("Furniture", re.getBody().get(1).getType());
	    
	    verify(lservice, times(1)).listAll();
	    
	    
	}
	
	@Test
	void testGetAllloancards_fail() throws ParseException{
		LoanCard lc1 = new LoanCard();
		LoanCard lc2 = new LoanCard();
		
		lc1.setType("Crockery");
		lc1.setDuration(5);
		
		EmployeeCard ec1 = new EmployeeCard();
		Employee e1 = new Employee();
		
		e1.setEmail("arpan@example.com");
	    e1.setFname("arpan");
	    e1.setLname("ghosh");
	    e1.setGender("male");
	    e1.setDepartment("CT");
	    e1.setDesignation("Program Associate");
	    
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    Date dob=new Date(df.parse("1985-01-01").getTime());
	    e1.setDob(dob);
	    Date doj=new Date(df.parse("2023-08-23").getTime());
	    e1.setDoj(doj);
	    e1.setPassword("password");
	    
	    ec1.setEmployee(e1);
	    ec1.setLoanId(lc1);
	    ec1.setIssueDate(doj);
	    
	    List<EmployeeCard> ecs1 = new ArrayList<EmployeeCard>();
	    ecs1.add(ec1);
	    
	    lc1.setEmployeeCard(ecs1);
	    
	    lc2.setType("Furniture");
		lc2.setDuration(3);
		
		EmployeeCard ec2 = new EmployeeCard();
		Employee e2 = new Employee();
		
		e2.setEmail("anubhav@example.com");
	    e2.setFname("anubhav");
	    e2.setLname("rajput");
	    e2.setGender("male");
	    e2.setDepartment("TCOO");
	    e2.setDesignation("Program Associate");
	    
	    SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
	    Date dob2=new Date(df2.parse("2001-04-18").getTime());
	    e2.setDob(dob2);
	    Date doj2=new Date(df2.parse("2023-07-21").getTime());
	    e2.setDoj(doj2);
	    e2.setPassword("password");
	    
	    ec2.setEmployee(e2);
	    ec2.setLoanId(lc2);
	    ec2.setIssueDate(doj);
	    
	    List<EmployeeCard> ecs2 = new ArrayList<EmployeeCard>();
	    ecs2.add(ec2);
	    
	    lc2.setEmployeeCard(ecs2);
	    
	    List<LoanCard> list = new ArrayList<LoanCard>();
	    list.add(lc1);
	    list.add(lc2);
	    
	    Exception ex = new IllegalStateException();
	    when(lservice.listAll()).thenThrow(ex);
	    
	    ResponseEntity<List<LoanCard>> re = loanCardController.getAllloancards();
	    
	    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, re.getStatusCode());
	    assertNull(re.getBody());
	    
	    verify(lservice, times(1)).listAll();
	}

	@Test
	void testGetLoanCardById() throws ParseException, ResourceNotFoundException {
		lc.setLoanId(1L);
		lc.setType("Crockery");
		lc.setDuration(5);
		
		EmployeeCard ec = new EmployeeCard();
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
	    
	    ec.setEmployee(e);
	    ec.setLoanId(lc);
	    ec.setIssueDate(doj);
	    
	    List<EmployeeCard> ecs = new ArrayList<EmployeeCard>();
	    ecs.add(ec);
	    
	    lc.setEmployeeCard(ecs);
	    
	    when(lservice.getSingleLoanCard(1L)).thenReturn(Optional.of(lc));
	    
	    ResponseEntity<LoanCard> re = loanCardController.getLoanCardById(1L);
	    
	    assertEquals(HttpStatus.OK, re.getStatusCode());
	    assertEquals("Crockery", re.getBody().getType());
	    assertEquals(5, re.getBody().getDuration());
	    
	    verify(lservice, times(1)).getSingleLoanCard(1L);
	    
	    
	}
	
	@Test
	void testGetLoanCardById_fail() throws ParseException, ResourceNotFoundException {
		lc.setLoanId(1L);
		lc.setType("Crockery");
		lc.setDuration(5);
		
		EmployeeCard ec = new EmployeeCard();
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
	    
	    ec.setEmployee(e);
	    ec.setLoanId(lc);
	    ec.setIssueDate(doj);
	    
	    List<EmployeeCard> ecs = new ArrayList<EmployeeCard>();
	    ecs.add(ec);
	    
	    lc.setEmployeeCard(ecs);
	    when(lservice.getSingleLoanCard(1L)).thenReturn(Optional.empty());
	    try {
	    	ResponseEntity<LoanCard> re = loanCardController.getLoanCardById(1L);
	    }
	    catch (Exception ex) {
	    	assertEquals("LoanCard not found for this id"+lc.getLoanId() ,ex.getMessage());
	    }
	    
	    verify(lservice, times(1)).getSingleLoanCard(1L);
	    
	}

	@Test
	void testUpdateLoanCard() throws ParseException, ResourceNotFoundException {
		LoanCard lc1 = new LoanCard();
		LoanCard lc2 = new LoanCard();
		
		lc1.setType("Crockery");
		lc1.setDuration(5);
		
		EmployeeCard ec1 = new EmployeeCard();
		Employee e1 = new Employee();
		
		e1.setEmail("arpan@example.com");
	    e1.setFname("arpan");
	    e1.setLname("ghosh");
	    e1.setGender("male");
	    e1.setDepartment("CT");
	    e1.setDesignation("Program Associate");
	    
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    Date dob=new Date(df.parse("1985-01-01").getTime());
	    e1.setDob(dob);
	    Date doj=new Date(df.parse("2023-08-23").getTime());
	    e1.setDoj(doj);
	    e1.setPassword("password");
	    
	    ec1.setEmployee(e1);
	    ec1.setLoanId(lc1);
	    ec1.setIssueDate(doj);
	    
	    List<EmployeeCard> ecs1 = new ArrayList<EmployeeCard>();
	    ecs1.add(ec1);
	    
	    lc1.setEmployeeCard(ecs1);
	    
	    lc2.setType("Crockery");
		lc2.setDuration(3);
		lc2.setEmployeeCard(ecs1);
		
		lc1.setLoanId(1L);
		
		when(lservice.getSingleLoanCard(1L)).thenReturn(Optional.of(lc1));
		when(lservice.addLoanCard(any(LoanCard.class))).thenReturn(lc2);
		
		ResponseEntity<LoanCard> re = loanCardController.updateLoanCard(1L, lc2);
		
		assertEquals("Crockery", re.getBody().getType());
		assertEquals(3, re.getBody().getDuration());
		
		verify(lservice, times(1)).getSingleLoanCard(1L);
		verify(lservice, times(1)).addLoanCard(any(LoanCard.class));
	    
	    
	    
	}
	
	@Test
	void testUpdateLoanCard_fail() throws ParseException, ResourceNotFoundException {
		LoanCard lc1 = new LoanCard();
		LoanCard lc2 = new LoanCard();
		
		lc1.setType("Crockery");
		lc1.setDuration(5);
		
		EmployeeCard ec1 = new EmployeeCard();
		Employee e1 = new Employee();
		
		e1.setEmail("arpan@example.com");
	    e1.setFname("arpan");
	    e1.setLname("ghosh");
	    e1.setGender("male");
	    e1.setDepartment("CT");
	    e1.setDesignation("Program Associate");
	    
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    Date dob=new Date(df.parse("1985-01-01").getTime());
	    e1.setDob(dob);
	    Date doj=new Date(df.parse("2023-08-23").getTime());
	    e1.setDoj(doj);
	    e1.setPassword("password");
	    
	    ec1.setEmployee(e1);
	    ec1.setLoanId(lc1);
	    ec1.setIssueDate(doj);
	    
	    List<EmployeeCard> ecs1 = new ArrayList<EmployeeCard>();
	    ecs1.add(ec1);
	    
	    lc1.setEmployeeCard(ecs1);
	    
	    lc2.setType("Crockery");
		lc2.setDuration(3);
		lc2.setEmployeeCard(ecs1);
		
		lc1.setLoanId(1L);
		
		when(lservice.getSingleLoanCard(1L)).thenReturn(Optional.empty());
		
		try {
			ResponseEntity<LoanCard> re = loanCardController.updateLoanCard(1L, lc2);
		}
		catch(Exception ex) {
			assertEquals("LoanCard not found for this id"+lc1.getLoanId() ,ex.getMessage());
		}
		
		verify(lservice, times(1)).getSingleLoanCard(1L);
	}

	@Test
	void testDeleteLoanCard() throws ParseException, ResourceNotFoundException {
		lc.setLoanId(1L);
		lc.setType("Crockery");
		lc.setDuration(5);
		
		EmployeeCard ec = new EmployeeCard();
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
	    
	    ec.setEmployee(e);
	    ec.setLoanId(lc);
	    ec.setIssueDate(doj);
	    
	    List<EmployeeCard> ecs = new ArrayList<EmployeeCard>();
	    ecs.add(ec);
	    
	    lc.setEmployeeCard(ecs);
	    
	    when(lservice.getSingleLoanCard(1L)).thenReturn(Optional.of(lc));
	    doNothing().when(lservice).deleteLoanCard(1L);
	    
	    ResponseEntity<Map<String, Boolean>> re = loanCardController.deleteLoanCard(1L);
	    
	    assertTrue(re.getBody().containsKey("Deleted"));
		assertTrue(re.getBody().get("Deleted"));
		
		verify(lservice, times(1)).getSingleLoanCard(1L);
		verify(lservice, times(1)).deleteLoanCard(1L);
	    
	}
	
	@Test
	void testDeleteLoanCard_fail() throws ParseException, ResourceNotFoundException {
		lc.setLoanId(1L);
		lc.setType("Crockery");
		lc.setDuration(5);
		
		EmployeeCard ec = new EmployeeCard();
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
	    
	    ec.setEmployee(e);
	    ec.setLoanId(lc);
	    ec.setIssueDate(doj);
	    
	    List<EmployeeCard> ecs = new ArrayList<EmployeeCard>();
	    ecs.add(ec);
	    
	    lc.setEmployeeCard(ecs);
	    
	    when(lservice.getSingleLoanCard(1L)).thenReturn(Optional.empty());
	    
	    try {
	    	ResponseEntity<Map<String, Boolean>> re = loanCardController.deleteLoanCard(1L);
	    }
	    catch(Exception ex) {
	    	assertEquals("LoanCard not found for this Id: "+lc.getLoanId() ,ex.getMessage());
	    }
	    
	    verify(lservice, times(1)).getSingleLoanCard(1L);
	    
	}
	
	@Test
	void getLoanCardTypesTest() throws ParseException {
		LoanCard lc1 = new LoanCard();
		LoanCard lc2 = new LoanCard();
		
		lc1.setType("Crockery");
		lc1.setDuration(5);
		
		EmployeeCard ec1 = new EmployeeCard();
		Employee e1 = new Employee();
		
		e1.setEmail("arpan@example.com");
	    e1.setFname("arpan");
	    e1.setLname("ghosh");
	    e1.setGender("male");
	    e1.setDepartment("CT");
	    e1.setDesignation("Program Associate");
	    
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    Date dob=new Date(df.parse("1985-01-01").getTime());
	    e1.setDob(dob);
	    Date doj=new Date(df.parse("2023-08-23").getTime());
	    e1.setDoj(doj);
	    e1.setPassword("password");
	    
	    ec1.setEmployee(e1);
	    ec1.setLoanId(lc1);
	    ec1.setIssueDate(doj);
	    
	    List<EmployeeCard> ecs1 = new ArrayList<EmployeeCard>();
	    ecs1.add(ec1);
	    
	    lc1.setEmployeeCard(ecs1);
	    
	    lc2.setType("Furniture");
		lc2.setDuration(3);
		
		EmployeeCard ec2 = new EmployeeCard();
		Employee e2 = new Employee();
		
		e2.setEmail("anubhav@example.com");
	    e2.setFname("anubhav");
	    e2.setLname("rajput");
	    e2.setGender("male");
	    e2.setDepartment("TCOO");
	    e2.setDesignation("Program Associate");
	    
	    SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
	    Date dob2=new Date(df2.parse("2001-04-18").getTime());
	    e2.setDob(dob2);
	    Date doj2=new Date(df2.parse("2023-07-21").getTime());
	    e2.setDoj(doj2);
	    e2.setPassword("password");
	    
	    ec2.setEmployee(e2);
	    ec2.setLoanId(lc2);
	    ec2.setIssueDate(doj);
	    
	    List<EmployeeCard> ecs2 = new ArrayList<EmployeeCard>();
	    ecs2.add(ec2);
	    
	    lc2.setEmployeeCard(ecs2);
	    
	    List<LoanCard> list = new ArrayList<LoanCard>();
	    list.add(lc1);
	    list.add(lc2);
	    
	    List<String> typelist=new ArrayList<>();
	    
	    typelist.add("Crockery");
	    typelist.add("Furniture");
	    
	    when(lservice.listAll()).thenReturn(list);
	    
	    ResponseEntity<List<String>> re=loanCardController.getLoanCardTypes();
	    
	    assertEquals(re.getStatusCode(), HttpStatus.OK);
	    assertEquals(re.getBody(), typelist);
	    
	    verify(lservice, times(1)).listAll();
		
	}
	
	@Test
	void getLoanCardTypesTest_Fail() throws ParseException {
		LoanCard lc1 = new LoanCard();
		LoanCard lc2 = new LoanCard();
		
		lc1.setType("Crockery");
		lc1.setDuration(5);
		
		EmployeeCard ec1 = new EmployeeCard();
		Employee e1 = new Employee();
		
		e1.setEmail("arpan@example.com");
	    e1.setFname("arpan");
	    e1.setLname("ghosh");
	    e1.setGender("male");
	    e1.setDepartment("CT");
	    e1.setDesignation("Program Associate");
	    
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    Date dob=new Date(df.parse("1985-01-01").getTime());
	    e1.setDob(dob);
	    Date doj=new Date(df.parse("2023-08-23").getTime());
	    e1.setDoj(doj);
	    e1.setPassword("password");
	    
	    ec1.setEmployee(e1);
	    ec1.setLoanId(lc1);
	    ec1.setIssueDate(doj);
	    
	    List<EmployeeCard> ecs1 = new ArrayList<EmployeeCard>();
	    ecs1.add(ec1);
	    
	    lc1.setEmployeeCard(ecs1);
	    
	    lc2.setType("Furniture");
		lc2.setDuration(3);
		
		EmployeeCard ec2 = new EmployeeCard();
		Employee e2 = new Employee();
		
		e2.setEmail("anubhav@example.com");
	    e2.setFname("anubhav");
	    e2.setLname("rajput");
	    e2.setGender("male");
	    e2.setDepartment("TCOO");
	    e2.setDesignation("Program Associate");
	    
	    SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
	    Date dob2=new Date(df2.parse("2001-04-18").getTime());
	    e2.setDob(dob2);
	    Date doj2=new Date(df2.parse("2023-07-21").getTime());
	    e2.setDoj(doj2);
	    e2.setPassword("password");
	    
	    ec2.setEmployee(e2);
	    ec2.setLoanId(lc2);
	    ec2.setIssueDate(doj);
	    
	    List<EmployeeCard> ecs2 = new ArrayList<EmployeeCard>();
	    ecs2.add(ec2);
	    
	    lc2.setEmployeeCard(ecs2);
	    
	    List<LoanCard> list = new ArrayList<LoanCard>();
	    list.add(lc1);
	    list.add(lc2);
	    
	    List<String> typelist=new ArrayList<>();
	    
	    typelist.add("Crockery");
	    typelist.add("Furniture");
	    
	    Exception ex=new IllegalStateException();
	    when(lservice.listAll()).thenThrow(ex);
	    
	    ResponseEntity<List<String>> re=loanCardController.getLoanCardTypes();
	    
	    assertEquals(re.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
	    assertNull(re.getBody());
	    
	    verify(lservice, times(1)).listAll();
		
	}

}
