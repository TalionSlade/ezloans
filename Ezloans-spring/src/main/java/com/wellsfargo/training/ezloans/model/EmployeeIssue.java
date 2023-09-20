package com.wellsfargo.training.ezloans.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

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
@Table(name="employee_issue_details")
public class EmployeeIssue {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="issue_id")
	private Long issueId;

	@ManyToOne
	@JoinColumn(name = "eid")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name = "itemId")
	private Item item;
	
	@Column(nullable = false, name="issue_date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date issueDate;
	
	@Column(nullable = false, name="return_date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date returnDate;
	
	public EmployeeIssue() {
		super();
	}

	public Long getIssueId() {
		return issueId;
	}

	public void setIssueId(Long issueId) {
		this.issueId = issueId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	
}
