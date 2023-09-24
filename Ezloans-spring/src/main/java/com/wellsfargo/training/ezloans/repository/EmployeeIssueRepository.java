package com.wellsfargo.training.ezloans.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wellsfargo.training.ezloans.model.EmployeeIssue;

public interface EmployeeIssueRepository extends JpaRepository<EmployeeIssue, Long>{
	

	
	@Query(value = "SELECT * FROM EMPLOYEE_ISSUE_DETAILS WHERE EID = ?1", nativeQuery = true)
	List<EmployeeIssue> getEmployeeIssuesOfEmployee(Long eid);

}

