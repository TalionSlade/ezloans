package com.wellsfargo.training.ezloans.model;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
public class EmployeeCard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private long employe_card_id;
	
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name = "loan_id")
	private LoanCard loan_id;
	
	
	@Column
	private Date card_issue_date;

	public EmployeeCard() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
