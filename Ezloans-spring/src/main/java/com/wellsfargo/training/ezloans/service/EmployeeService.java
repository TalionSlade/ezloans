package com.wellsfargo.training.ezloans.service;

import java.util.List;
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
	
	public Optional<Employee> loginEmployee(String email){
		return erepo.findByEmail(email);
		
	}
	public Optional<Employee> getEmployee(long id){
		return erepo.findById(id);
	}
	
	public List<Employee> listAll() {
		return erepo.findAll();  
		
	}
	
	public void deleteEmployee(long id) {
		erepo.deleteById(id);
	}
	
	public Employee saveEmployee(Employee e) {
		return erepo.save(e); 
	}
	
	public Long getEmployeeCount() {
		return erepo.count();
	}
	
	public Optional<Employee> getSingleEmployee(long id) {
		return erepo.findById(id);
	}

}
