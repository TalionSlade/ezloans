package com.wellsfargo.training.ezloans.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wellsfargo.training.ezloans.model.LoanCard;



public interface LoanCardRepository extends JpaRepository<LoanCard, Long>{
	
	@Query(value = "SELECT * FROM LOAN_CARD_MASTER ", nativeQuery = true)
	public List<LoanCard>getLoanCardsOfEmployee(Long id);



}
