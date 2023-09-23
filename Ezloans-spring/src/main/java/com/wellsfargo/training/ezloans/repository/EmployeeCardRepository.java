package com.wellsfargo.training.ezloans.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wellsfargo.training.ezloans.model.EmployeeCard;

public interface EmployeeCardRepository extends JpaRepository<EmployeeCard, Long>{

}
