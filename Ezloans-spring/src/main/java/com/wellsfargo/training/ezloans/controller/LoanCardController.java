package com.wellsfargo.training.ezloans.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.training.ezloans.model.Employee;
import com.wellsfargo.training.ezloans.model.EmployeeCardAndLoanCardProjection;
import com.wellsfargo.training.ezloans.model.LoanCard;
import com.wellsfargo.training.ezloans.service.LoanCardService;


@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping(value="/api")
public class LoanCardController {

	@Autowired
	private LoanCardService lservice;
	
	@PostMapping("/addloancard")
	public ResponseEntity<String> addLoanCard(@Validated @RequestBody LoanCard loanCard) {
    try {
			
			LoanCard loan=lservice.addLoanCard(loanCard);
			if(loan!=null) {
				return ResponseEntity.ok("Loan Card added Successfully");
			}
			else
				return ResponseEntity.badRequest().body("Failed to add Loan Card");
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An Error has Occured: "+ e.getMessage());
		}
	}
	
	@GetMapping("/loancards")
	public ResponseEntity<List<EmployeeCardAndLoanCardProjection>> getLoanCardsInfo(){
		try {
			List<EmployeeCardAndLoanCardProjection> selectedFields=lservice.getLoanCardsInfo();
			return ResponseEntity.ok(selectedFields);
					}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}
