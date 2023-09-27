package com.wellsfargo.training.ezloans.controller;


import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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
//	@Test
//	void testSaveProduct() {
//		i.setDes("Soundbar");
//		i.setBrand("Boat");
//		i.setMadein("India");
//		i.setPrice(5000.0f);
//		
//		//mockito methods
//		//enables stubbing methods
//		when(pservice.saveProduct(any(Product.class))).thenReturn(p);
//		
//		ResponseEntity<Product> re=productController.saveProduct(p);
//		
//		assertEquals(HttpStatus.CREATED, re.getStatusCode());
//		assertEquals("Soundbar", re.getBody().getName());
//		assertEquals("Boat", re.getBody().getBrand());
//		assertEquals("India", re.getBody().getMadein());
//		assertEquals(5000.0f, re.getBody().getPrice());
//		
//		//verify method is used to check whether some specified methods are called or not
//		verify(pservice, times(1)).saveProduct(any(Product.class));
//		
//	}
//	
}
