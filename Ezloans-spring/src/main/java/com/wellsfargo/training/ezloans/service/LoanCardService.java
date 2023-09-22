package com.wellsfargo.training.ezloans.service;

import java.util.List;
import java.util.Optional;

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
	
	public List<LoanCard> listAll(){
		return lrepo.findAll(); //Invoke findAll() method of Jpa repository
	}
	
	public Optional<LoanCard> getSingleLoanCard(long lid){
		return lrepo.findById(lid);
	}
	
	public void deleteLoanCard(long lid) {
		 lrepo.deleteById(lid);
	}
	
	public List<LoanCard> getLoanCardsOfEmployee(Long id){
		return lrepo.getLoanCardsOfEmployee(id); //Invoke findAll() method of Jpa repository
	}
	
	
	

	

}
