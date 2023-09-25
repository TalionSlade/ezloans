package com.wellsfargo.training.ezloans.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.training.ezloans.model.EmployeeCard;
import com.wellsfargo.training.ezloans.model.Item;
import com.wellsfargo.training.ezloans.repository.EmployeeCardRepository;
import com.wellsfargo.training.ezloans.repository.ItemRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeCardService {
	
	@Autowired
	private EmployeeCardRepository ecardrepo;
	
	public EmployeeCard addEmployeeCard(EmployeeCard ecard) {
		
		return ecardrepo.save(ecard);
		
	}
	
public List<EmployeeCard> getEmployeeCards() {
		
		return ecardrepo.findAll();
		
	}

}
