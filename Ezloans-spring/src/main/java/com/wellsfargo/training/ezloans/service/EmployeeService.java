package com.wellsfargo.training.ezloans.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.training.ezloans.model.Employee;
import com.wellsfargo.training.ezloans.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository erepo;
	
	public Employee registerEmployee(Employee employee) {
		return erepo.save(employee);
	}
	
	public Optional<Employee> getEmployee(Long eid) {
		return erepo.findById(eid);
	}
	
	public Optional<Employee> loginEmployee(String email){
		return erepo.findByEmail(email);
		
	}
	public Optional<Employee> getEmployee(long id){
		return erepo.findById(id);
	}


}
