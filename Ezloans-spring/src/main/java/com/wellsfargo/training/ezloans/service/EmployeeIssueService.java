package com.wellsfargo.training.ezloans.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
    public List<EmployeeIssue> listProductByEid(Long eid){
    	return eisrepo.findItemEmployeeIssueProjection(eid);
    }
}

