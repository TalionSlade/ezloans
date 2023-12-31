package com.wellsfargo.training.ezloans.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.training.ezloans.model.Employee;
import com.wellsfargo.training.ezloans.model.LoanCard;
import com.wellsfargo.training.ezloans.service.LoanCardService;
import com.wellsfargo.training.ezloans.exception.ResourceNotFoundException;


@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping(value="/api")
public class LoanCardController {

	@Autowired
	private LoanCardService lservice;
	
	//This function is used to add loan card to a table.
	@PostMapping("/loancards")
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
	/*Sample Json
	 * {
    "type":"Crockery",
    "duration":10
    
    */
	
	
	//This function is used to get all loan cards.
	@GetMapping("/loancards")
	public ResponseEntity<List<LoanCard>> getAllloancards(){
	
		try {
			List<LoanCard>loancards=lservice.listAll();
			return ResponseEntity.ok(loancards);
		}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
			
		}
	}
	
	//This function is used to get loan cards having particular types.
	@GetMapping("/loancards/types")
	public ResponseEntity<List<String>> getLoanCardTypes(){
		try {
			List<LoanCard>loancards=lservice.listAll();
			List<String>loancardtypes=new ArrayList<>();
			for(LoanCard l:loancards) {
				loancardtypes.add(l.getType());
			}
			return ResponseEntity.ok(loancardtypes);
		}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
			
		}
	}
	
	//This function is used to get loancards having a particular id.
	@GetMapping("/loancards/{id}")
	public ResponseEntity<LoanCard> getLoanCardById(@PathVariable(value="id") long lid)throws ResourceNotFoundException{
		LoanCard l=lservice.getSingleLoanCard(lid).orElseThrow(()->new ResourceNotFoundException("LoanCard not found for this id"+lid));
		return ResponseEntity.ok().body(l);
	}
	
	//This function is used to update loan cards having a particular id with a new loan card object
	@PutMapping("/loancards/{id}")
	public ResponseEntity<LoanCard> updateLoanCard(@PathVariable(value="id") long lid,@Validated @RequestBody LoanCard l)throws ResourceNotFoundException{
		LoanCard loanCard=lservice.getSingleLoanCard(lid).orElseThrow(()->new ResourceNotFoundException("LoanCard not found for this id"+lid));
		loanCard.setType(l.getType());
		loanCard.setDuration(l.getDuration());
	
	LoanCard updatedLoanCard=lservice.addLoanCard(loanCard);
	
	return ResponseEntity.ok().body(updatedLoanCard) ;
	}
	
	
	//This function is used to delete loan cards having a particular id.
	@DeleteMapping("/loancards/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteLoanCard(@PathVariable(value="id") Long lId)
	throws ResourceNotFoundException
	{
		lservice.getSingleLoanCard(lId).
		orElseThrow(()-> new ResourceNotFoundException("LoanCard not found for this Id: "+lId));
		
		lservice.deleteLoanCard(lId);
		
		Map<String,Boolean> response=new HashMap<String, Boolean>();
		response.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	



	


    
}
	 