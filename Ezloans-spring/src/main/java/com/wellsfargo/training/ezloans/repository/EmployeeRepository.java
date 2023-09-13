package com.wellsfargo.training.ezloans.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wellsfargo.training.ezloans.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	
	public Optional<Employee> findByEmail(String email);

}
