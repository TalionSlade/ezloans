package com.wellsfargo.training.ezloans.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeCard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable=false,name = "employee_card_id")
	private long cardId;
	
	@ManyToOne
	@JoinColumn(nullable=false,name = "eid")
	@JsonManagedReference
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(nullable=false,name = "loanId")
	@JsonManagedReference
	private LoanCard loanId;
	
	
	@Column(nullable=false,name="card_issue_date")
	private Date issueDate;

//	public EmployeeCard() {
//		super();
//	}
//	
//	
//
//	public EmployeeCard(long cardId, Date issueDate) {
//		super();
//		this.cardId = cardId;
//		this.issueDate = issueDate;
//	}
//
//
//
//	public EmployeeCard(long cardId, Employee employee, LoanCard loanId, Date issueDate) {
//		super();
//		this.cardId = cardId;
//		this.employee = employee;
//		this.loanId = loanId;
//		this.issueDate = issueDate;
//	}
//	
//	public EmployeeCard(long cardId, LoanCard loanId, Date issueDate) {
//		super();
//		this.cardId = cardId;
//		
//		this.loanId = loanId;
//		this.issueDate = issueDate;
//	}
//	
//	public EmployeeCard(long cardId, Employee employee, Date issueDate) {
//		super();
//		this.cardId = cardId;
//		this.employee = employee;
//	
//		this.issueDate = issueDate;
//	}
//
//
//
//	public long getCardId() {
//		return cardId;
//	}
//
//	public void setCardId(long cardId) {
//		this.cardId = cardId;
//	}
//
////	public Employee getEmployee() {
////		return employee;
////	}
////
////	public void setEmployee(Employee employee) {
////		this.employee = employee;
////	}
//
//	public LoanCard getLoanId() {
//		return loanId;
//	}
//
//	public void setLoanId(LoanCard loanId) {
//		this.loanId = loanId;
//	}
//
//	public Date getIssueDate() {
//		return issueDate;
//	}
//
//	public void setIssueDate(Date issueDate) {
//		this.issueDate = issueDate;
//	}
//	
//	
//	
//	
	
}
