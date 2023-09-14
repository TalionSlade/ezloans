package com.wellsfargo.training.ezloans.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wellsfargo.training.ezloans.model.LoanCard;

public interface LoanCardRepository extends JpaRepository<LoanCard, Long>{

}
