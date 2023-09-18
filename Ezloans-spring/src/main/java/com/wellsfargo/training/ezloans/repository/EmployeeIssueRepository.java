package com.wellsfargo.training.ezloans.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wellsfargo.training.ezloans.model.EmployeeIssue;

public interface EmployeeIssueRepository extends JpaRepository<EmployeeIssue, Long>{
	
	public Optional<EmployeeIssue> findByEmployee(String eid);

}
