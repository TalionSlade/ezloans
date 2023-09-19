package com.wellsfargo.training.ezloans.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wellsfargo.training.ezloans.model.EmployeeCardAndLoanCardProjection;
import com.wellsfargo.training.ezloans.model.LoanCard;


public interface LoanCardRepository extends JpaRepository<LoanCard, Long>{
//	@Query("SELECT new com.wellsfargo.training.model.AddressAndDealerProjection"+"(d.id,d.fname,d.lname,d.phoneNo,d.email,"+"a.street,a.city,a.pincode)"+"FROM Dealer d join d.Address a")
	@Query("SELECT new com.wellsfargo.training.ezloans.model.EmployeeCardAndLoanCardProjection"
			+ "(e.cardId,e.loanId,e.issueDate,"
			+ "l.loanId,l.type,l.duration)"
			+ "FROM LoanCard l JOIN l.employeeCard e")	
	List<EmployeeCardAndLoanCardProjection> findSelectedFieldsFromEmployeeCardAndLoanCard();

}
