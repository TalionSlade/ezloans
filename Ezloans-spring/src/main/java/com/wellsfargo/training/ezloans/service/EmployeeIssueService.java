package com.wellsfargo.training.ezloans.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wellsfargo.training.ezloans.model.Employee;
import com.wellsfargo.training.ezloans.model.EmployeeCard;
import com.wellsfargo.training.ezloans.model.EmployeeIssue;
import com.wellsfargo.training.ezloans.repository.EmployeeIssueRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeIssueService {
	
	@Autowired
	private EmployeeIssueRepository eisrepo;
	
	public EmployeeIssue registerEmployeeIssue(EmployeeIssue ei) {
		return eisrepo.save(ei);
	}
	
	public List<EmployeeIssue> getEmployeeIssuesOfEmployee(Long id){
		return eisrepo.getEmployeeIssuesOfEmployee(id);
	}

}