package com.wellsfargo.training.ezloans.model;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
public class EmployeeCard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_card_id")
	private long cardId;
	
	@ManyToOne
	@JoinColumn(name = "eid")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name = "loanId")
	private LoanCard loanId;
	
	
	@Column(name="card_issue_date")
	private Date issueDate;

	public EmployeeCard() {
		super();
	}
	
	

	public EmployeeCard(long cardId, Date issueDate) {
		super();
		this.cardId = cardId;
		this.issueDate = issueDate;
	}



	public EmployeeCard(long cardId, Employee employee, LoanCard loanId, Date issueDate) {
		super();
		this.cardId = cardId;
		this.employee = employee;
		this.loanId = loanId;
		this.issueDate = issueDate;
	}
	
	public EmployeeCard(long cardId, LoanCard loanId, Date issueDate) {
		super();
		this.cardId = cardId;
		
		this.loanId = loanId;
		this.issueDate = issueDate;
	}
	
	public EmployeeCard(long cardId, Employee employee, Date issueDate) {
		super();
		this.cardId = cardId;
		this.employee = employee;
	
		this.issueDate = issueDate;
	}



	public long getCardId() {
		return cardId;
	}

	public void setCardId(long cardId) {
		this.cardId = cardId;
	}

//	public Employee getEmployee() {
//		return employee;
//	}
//
//	public void setEmployee(Employee employee) {
//		this.employee = employee;
//	}

	public LoanCard getLoanId() {
		return loanId;
	}

	public void setLoanId(LoanCard loanId) {
		this.loanId = loanId;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	
	
	
	
	
}
