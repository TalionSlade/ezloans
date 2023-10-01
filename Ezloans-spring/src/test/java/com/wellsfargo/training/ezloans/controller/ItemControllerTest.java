package com.wellsfargo.training.ezloans.controller;


import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import com.wellsfargo.training.ezloans.model.EmployeeIssue;
import com.wellsfargo.training.ezloans.model.Item;
import com.wellsfargo.training.ezloans.service.ItemService;


@SpringBootTest
@DisplayName("Items CRUD tests")
public class ItemControllerTest {


	@Autowired
	private ItemController ItemController;
	
	private Item i;
	
	@MockBean // create a mock object
	private ItemService iservice;
	
	@BeforeEach
	void setUp() throws Exception {
		i=new Item();
	}

	@AfterEach
	void tearDown() throws Exception {
		i=null;
	}
	@Test
	void testSaveItem() throws ParseException {
		
		i.setDesc("Soundbar");
		i.setStatus("New");
		i.setMake("Wooden");
		i.setValuation(5000);
		i.setCategory("Appliance");
		
		//setting up the Dummy Item Issue
		EmployeeIssue ei = new EmployeeIssue();
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

	    ei.setEmployee(e);
	    ei.setItem(i);
	    ei.setIssueDate(doj);

	    List<EmployeeIssue> eis = new ArrayList<EmployeeIssue>();
	    eis.add(ei);

	    i.setEmployeeIssue(eis);
	    		
		
	    when(iservice.registerItems(any(Item.class))).thenReturn(i);

		ResponseEntity<String> re=ItemController.insertItem(i);

		assertEquals(HttpStatus.OK, re.getStatusCode());
		assertEquals("Item successfully registered", re.getBody());

		verify(iservice, times(1)).registerItems(any(Item.class));
		
	}
	@Test
	void testSaveItem_ReturnItem() throws ParseException {
		
		i.setDesc("Soundbar");
		i.setStatus("New");
		i.setMake("Wooden");
		i.setValuation(5000);
		i.setCategory("Appliance");
		
		//setting up the Dummy Item Issue
		EmployeeIssue ei = new EmployeeIssue();
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

	    ei.setEmployee(e);
	    ei.setItem(i);
	    ei.setIssueDate(doj);

	    List<EmployeeIssue> eis = new ArrayList<EmployeeIssue>();
	    eis.add(ei);

	    i.setEmployeeIssue(eis);
	    		
		
	    when(iservice.saveItem(any(Item.class))).thenReturn(i);

		ResponseEntity<Item> re=ItemController.saveProduct(i);

		assertEquals(HttpStatus.CREATED, re.getStatusCode());
//		assertEquals("Item successfully registered", re.getBody());

		verify(iservice, times(1)).saveItem(any(Item.class));
		
	}
	@Test
	void testSaveItem_ReturnItem_fail() throws ParseException {
		
		i.setDesc("Soundbar");
		i.setStatus("New");
		i.setMake("Wooden");
		i.setValuation(5000);
		i.setCategory("Appliance");
		
		//setting up the Dummy Item Issue
		EmployeeIssue ei = new EmployeeIssue();
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

	    ei.setEmployee(e);
	    ei.setItem(i);
	    ei.setIssueDate(doj);

	    List<EmployeeIssue> eis = new ArrayList<EmployeeIssue>();
	    eis.add(ei);

	    i.setEmployeeIssue(eis);
	    
	    Exception ex = new IllegalStateException();
	    when(iservice.saveItem(any(Item.class))).thenThrow(ex);

		ResponseEntity<Item> re=ItemController.saveProduct(i);

		assertNull(re.getBody());
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, re.getStatusCode());
		verify(iservice, times(1)).saveItem(any(Item.class));
		
	}
	
	@Test
	void testSaveItem_fail() throws ParseException {
		
		i.setDesc("Soundbar");
		i.setStatus("New");
		i.setMake("Wooden");
		i.setValuation(5000);
		i.setCategory("Appliance");
		
		//setting up the Dummy Item Issue
		EmployeeIssue ei = new EmployeeIssue();
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

	    ei.setEmployee(e);
	    ei.setItem(i);
	    ei.setIssueDate(doj);

	    List<EmployeeIssue> eis = new ArrayList<EmployeeIssue>();
	    eis.add(ei);

	    i.setEmployeeIssue(eis);
	    		
	    Exception ex = new IllegalStateException();
	    when(iservice.registerItems(any(Item.class))).thenThrow(ex);

		ResponseEntity<String> re=ItemController.insertItem(i);
		
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, re.getStatusCode());
		assertEquals("Error Occured :- "+ex.getMessage(), re.getBody());

		verify(iservice, times(1)).registerItems(any(Item.class));		
	}
	
	
	@Test
	void testSaveItem_else() throws ParseException {
		
		i.setDesc("Soundbar");
		i.setStatus("New");
		i.setMake("Wooden");
		i.setValuation(5000);
		i.setCategory("Appliance");
		
		//setting up the Dummy Item Issue
		EmployeeIssue ei = new EmployeeIssue();
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

	    ei.setEmployee(e);
	    ei.setItem(i);
	    ei.setIssueDate(doj);

	    List<EmployeeIssue> eis = new ArrayList<EmployeeIssue>();
	    eis.add(ei);

	    i.setEmployeeIssue(eis);
	    		
	    when(iservice.registerItems(any(Item.class))).thenReturn(null);

		ResponseEntity<String> re=ItemController.insertItem(i);

		assertEquals(HttpStatus.BAD_REQUEST, re.getStatusCode());
		assertEquals("Failed to add Item", re.getBody());

		verify(iservice, times(1)).registerItems(any(Item.class));
	}
	
	@Test
	void testGetAllItems_fail() throws ParseException {
		Item i1 = new Item();
		Item i2 = new Item();

		i1.setDesc("Soundbar");
		i1.setStatus("New");
		i1.setMake("Wooden");
		i1.setValuation(5000);
		i1.setCategory("Appliance");
		
		//setting up the Dummy Item Issue
		EmployeeIssue ei1 = new EmployeeIssue();
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

	    ei1.setEmployee(e1);
	    ei1.setItem(i);
	    ei1.setIssueDate(doj);

//	    i1.setEmployeeIssue(ei1);

		i2.setDesc("Soundbar");
		i2.setStatus("New");
		i2.setMake("Wooden");
		i2.setValuation(5000);
		i2.setCategory("Appliance");
		
		//setting up the Dummy Item Issue
		EmployeeIssue ei2 = new EmployeeIssue();
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

	    ei2.setEmployee(e2);
	    ei2.setItem(i);;
	    ei2.setIssueDate(doj);
	    
	    List<EmployeeIssue> eis = new ArrayList<EmployeeIssue>();
	    eis.add(ei1);
	    eis.add(ei2);

	    i.setEmployeeIssue(eis);
	    List<Item> list = new ArrayList<Item>();
	    list.add(i1);
	    list.add(i2);
	 
	    when(iservice.listAll()).thenReturn(list);

	    ResponseEntity<List<Item>> re = ItemController.getAllItems();

	    assertEquals(2, re.getBody().size());
	    assertEquals("Soundbar", re.getBody().get(0).getDesc());
	    assertEquals("Soundbar", re.getBody().get(1).getDesc());

	    verify(iservice, times(1)).listAll();
	}
	
	@Test
	void testGetAllItems() throws ParseException {
		Item i1 = new Item();
		Item i2 = new Item();

		i1.setDesc("Soundbar");
		i1.setStatus("New");
		i1.setMake("Wooden");
		i1.setValuation(5000);
		i1.setCategory("Appliance");
		
		//setting up the Dummy Item Issue
		EmployeeIssue ei1 = new EmployeeIssue();
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

	    ei1.setEmployee(e1);
	    ei1.setItem(i);
	    ei1.setIssueDate(doj);

//	    i1.setEmployeeIssue(ei1);

		i2.setDesc("Soundbar");
		i2.setStatus("New");
		i2.setMake("Wooden");
		i2.setValuation(5000);
		i2.setCategory("Appliance");
		
		//setting up the Dummy Item Issue
		EmployeeIssue ei2 = new EmployeeIssue();
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

	    ei2.setEmployee(e2);
	    ei2.setItem(i);;
	    ei2.setIssueDate(doj);
	    
	    List<EmployeeIssue> eis = new ArrayList<EmployeeIssue>();
	    eis.add(ei1);
	    eis.add(ei2);

	    i.setEmployeeIssue(eis);
	    List<Item> list = new ArrayList<Item>();
	    list.add(i1);
	    list.add(i2);
	 
	    Exception ex = new IllegalStateException();
	    when(iservice.listAll()).thenThrow(ex);
//	    when(iservice.listAll()).thenReturn(list);

	    ResponseEntity<List<Item>> re = ItemController.getAllItems();

	    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, re.getStatusCode());
	    assertNull(re.getBody());
	    verify(iservice, times(1)).listAll();
	}
	
	@Test
	void testGetItemById() throws ParseException, ResourceNotFoundException {
		i.setItemId(1L);
		
		i.setDesc("Soundbar");
		i.setStatus("New");
		i.setMake("Wooden");
		i.setValuation(5000);
		i.setCategory("Appliance");
		
		//setting up the Dummy Item Issue
		EmployeeIssue ei = new EmployeeIssue();
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

	    ei.setEmployee(e);
	    ei.setItem(i);
	    ei.setIssueDate(doj);

	    List<EmployeeIssue> ecs = new ArrayList<EmployeeIssue>();
	    ecs.add(ei);

	    i.setEmployeeIssue(ecs);

	    when(iservice.getItem(1L)).thenReturn(Optional.of(i));

	    ResponseEntity<Item> re = ItemController.getItemsById(1L);

	    assertEquals(HttpStatus.OK, re.getStatusCode());
	    assertEquals("Soundbar", re.getBody().getDesc());
	    assertEquals("Wooden", re.getBody().getMake());

	    verify(iservice, times(1)).getItem(1L);
	}
	
	@Test
	void testGetItemById_fail() throws ParseException, ResourceNotFoundException {
		i.setItemId(1L);
		
		i.setDesc("Soundbar");
		i.setStatus("New");
		i.setMake("Wooden");
		i.setValuation(5000);
		i.setCategory("Appliance");
		
		//setting up the Dummy Item Issue
		EmployeeIssue ei = new EmployeeIssue();
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

	    ei.setEmployee(e);
	    ei.setItem(i);
	    ei.setIssueDate(doj);

	    List<EmployeeIssue> ecs = new ArrayList<EmployeeIssue>();
	    ecs.add(ei);

	    i.setEmployeeIssue(ecs);
	    when(iservice.getItem(1L)).thenReturn(Optional.empty());
	    try {
	    	ResponseEntity<Item> re = ItemController.getItemsById(1L);
	    }catch(Exception ex) {
	    	assertEquals("Item not found for this id"+i.getItemId() ,ex.getMessage());
	    }
	   

	    verify(iservice, times(1)).getItem(1L);
	}
	
	@Test
	void testUpdateItem() throws ParseException, ResourceNotFoundException {
		
		Item i1 = new Item();
		Item i2 = new Item();

		i1.setDesc("Soundbar");
		i1.setStatus("New");
		i1.setMake("Wooden");
		i1.setValuation(5000);
		i1.setCategory("Appliance");
		
		
		EmployeeIssue ei1 = new EmployeeIssue();
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

	    ei1.setEmployee(e1);
	    ei1.setItem(i1);
	    ei1.setIssueDate(doj);

	    List<EmployeeIssue> eis1 = new ArrayList<EmployeeIssue>();
	    eis1.add(ei1);

	    i1.setEmployeeIssue(eis1);

	    i2.setDesc("Table");
	    i2.setStatus("New");
		i2.setMake("Wooden");
		i2.setValuation(5000);
		i2.setCategory("Appliance");
		i2.setEmployeeIssue(eis1);

		i1.setItemId(1L);

		when(iservice.getItem(1L)).thenReturn(Optional.of(i1));
		when(iservice.saveItem(any(Item.class))).thenReturn(i2);

		ResponseEntity<Item> re = ItemController.updateItem(1L, i2);

		assertEquals("Table", re.getBody().getDesc());
		assertEquals(5000, re.getBody().getValuation());

		verify(iservice, times(1)).getItem(1L);
		verify(iservice, times(1)).saveItem(any(Item.class));	
	}
	
	@Test
	void testUpdateItem_fail() throws ParseException, ResourceNotFoundException {
		
		Item i1 = new Item();
		Item i2 = new Item();

		i1.setDesc("Soundbar");
		i1.setStatus("New");
		i1.setMake("Wooden");
		i1.setValuation(5000);
		i1.setCategory("Appliance");
		
		
		EmployeeIssue ei1 = new EmployeeIssue();
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

	    ei1.setEmployee(e1);
	    ei1.setItem(i1);
	    ei1.setIssueDate(doj);

	    List<EmployeeIssue> eis1 = new ArrayList<EmployeeIssue>();
	    eis1.add(ei1);

	    i1.setEmployeeIssue(eis1);

	    i2.setDesc("Table");
	    i1.setStatus("New");
		i1.setMake("Wooden");
		i1.setValuation(5000);
		i1.setCategory("Appliance");
		i2.setEmployeeIssue(eis1);

		i1.setItemId(1L);

		when(iservice.getItem(1L)).thenReturn(Optional.empty());
		when(iservice.registerItems(any(Item.class))).thenReturn(i2);
		
		try {
			ResponseEntity<Item> re = ItemController.updateItem(1L, i2);
		}
		catch(Exception ex) {
			assertEquals("Item not found for this id "+i1.getItemId() ,ex.getMessage());
		}

		verify(iservice, times(1)).getItem(1L);
//		verify(iservice, times(1)).registerItems(any(Item.class));	
	}
	
	@Test
	void testDeleteItem() throws ParseException, ResourceNotFoundException {
		i.setItemId(1L);
		i.setDesc("Soundbar");
		i.setStatus("New");
		i.setMake("Wooden");
		i.setValuation(5000);
		i.setCategory("Appliance");

		EmployeeIssue ei = new EmployeeIssue();
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

	    ei.setEmployee(e);
	    ei.setItem(i);
	    ei.setIssueDate(doj);

	    List<EmployeeIssue> eis = new ArrayList<EmployeeIssue>();
	    eis.add(ei);

	    i.setEmployeeIssue(eis);

	    //.getItem(1L)).thenReturn(Optional.empty()
	    when(iservice.getItem(1L)).thenReturn(Optional.of(i));
	    doNothing().when(iservice).deleteItem(1L);

	    ResponseEntity<Map<String, Boolean>> re = ItemController.deleteItem(1L);

	    assertTrue(re.getBody().containsKey("Deleted Item"));
		assertTrue(re.getBody().get("Deleted Item"));

		verify(iservice, times(1)).getItem(1L);
		verify(iservice, times(1)).deleteItem(1L);

	}
	
	@Test
	void testDeleteItem_fail() throws ParseException, ResourceNotFoundException {
		i.setItemId(1L);
		i.setDesc("Soundbar");
		i.setStatus("New");
		i.setMake("Wooden");
		i.setValuation(5000);
		i.setCategory("Appliance");

		EmployeeIssue ei = new EmployeeIssue();
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

	    ei.setEmployee(e);
	    ei.setItem(i);
	    ei.setIssueDate(doj);

	    List<EmployeeIssue> eis = new ArrayList<EmployeeIssue>();
	    eis.add(ei);

	    i.setEmployeeIssue(eis);

	    //.getItem(1L)).thenReturn(Optional.empty
	    when(iservice.getItem(1L)).thenReturn(Optional.empty());
	    doNothing().when(iservice).deleteItem(1L);

	    try {
	    	ResponseEntity<Map<String, Boolean>> re = ItemController.deleteItem(1L);
	    }
	    catch(Exception ex) {
	    	assertEquals("Item not found for this id "+i.getItemId() ,ex.getMessage());
	    }
		verify(iservice, times(1)).getItem(1L);


	}
}
