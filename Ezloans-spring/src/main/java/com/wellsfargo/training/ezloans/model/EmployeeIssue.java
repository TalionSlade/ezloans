package com.wellsfargo.training.ezloans.model;

import java.sql.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="Employee_Issue_Details")
public class EmployeeIssue {
	
	@Id
//	@SequenceGenerator(name = "employee_issue_seq",initialValue = 100 , allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	//, generator = "employee_issue_seq")
	private Long issue_Id;

	
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name = "item_id")
	private Item item;
	
	@Column(nullable = false)
	private Date issue_date;
	
	@Column(nullable = false)
	private Date return_date;
	
	
	public EmployeeIssue() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
