package com.wellsfargo.training.ezloans.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wellsfargo.training.ezloans.model.EmployeeCard;
import com.wellsfargo.training.ezloans.model.LoanCard;

public interface EmployeeCardRepository extends JpaRepository<EmployeeCard, Long>{
	
	@Query(value = "SELECT * FROM EMPLOYEE_CARD WHERE EID = ?1 ", nativeQuery = true)
	public List<EmployeeCard>getEmployeeCardsOfEmployee(Long id);

}
