package com.wellsfargo.training.ezloans.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.wellsfargo.training.ezloans.model.LoanCard;
import com.wellsfargo.training.ezloans.repository.LoanCardRepository;


import jakarta.transaction.Transactional;

@Service
@Transactional
public class LoanCardService {
	
	@Autowired
	private LoanCardRepository lrepo;
	
	public LoanCard addLoanCard(LoanCard l) {
		return lrepo.save(l);
	}
	

}
