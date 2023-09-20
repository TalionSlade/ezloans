package com.wellsfargo.training.ezloans.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.training.ezloans.model.EmployeeCard;
import com.wellsfargo.training.ezloans.model.EmployeeIssue;
import com.wellsfargo.training.ezloans.repository.EmployeeCardRepository;
import com.wellsfargo.training.ezloans.repository.EmployeeIssueRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeIssueService {
	@Autowired
	private EmployeeIssueRepository eissuerepo;
	
	public EmployeeIssue addEmployeeIssue(EmployeeIssue eissue) {
		
		return eissuerepo.save(eissue);
		
	}
	
public List<EmployeeIssue> getEmployeeIssues() {
		
		return eissuerepo.findAll();
		
	}

}
