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
	
}
