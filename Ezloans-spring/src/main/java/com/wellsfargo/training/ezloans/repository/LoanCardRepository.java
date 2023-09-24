package com.wellsfargo.training.ezloans.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wellsfargo.training.ezloans.model.LoanCard;



public interface LoanCardRepository extends JpaRepository<LoanCard, Long>{
	
	public Optional<LoanCard> findByType(String type);


}
