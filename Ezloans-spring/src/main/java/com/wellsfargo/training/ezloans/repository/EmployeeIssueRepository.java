package com.wellsfargo.training.ezloans.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wellsfargo.training.ezloans.model.EmployeeIssue;

public interface EmployeeIssueRepository extends JpaRepository<EmployeeIssue, Long>{
	

	/*
	 * In order to define SQL to execute for a Spring Data repository method, we can annotate the method with the @Query annotation — its value attribute contains the JPQL or SQL to execute.

The @Query annotation takes precedence over named queries, which are annotated with @NamedQuery or defined in an orm.xml file.
	 */
	@Query(value = "SELECT * FROM EMPLOYEE_ISSUE_DETAILS WHERE EID = ?1", nativeQuery = true)
	List<EmployeeIssue> getEmployeeIssuesOfEmployee(Long eid);

}

