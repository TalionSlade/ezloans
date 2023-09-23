package com.wellsfargo.training.ezloans.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.wellsfargo.training.ezloans.model.LoanCard;


public interface LoanCardRepository extends JpaRepository<LoanCard, Long>{


}
