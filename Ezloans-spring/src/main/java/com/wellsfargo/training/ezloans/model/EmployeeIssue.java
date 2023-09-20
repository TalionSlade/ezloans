package com.wellsfargo.training.ezloans.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="employee_issue_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeIssue {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable=false,name="issue_id")
	private Long issueId;

	@ManyToOne
	@JoinColumn(nullable=false,name = "eid")
	@JsonManagedReference
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(nullable=false,name = "itemId")
	@JsonManagedReference
	private Item item;
	
	@Column(nullable = false, name="issue_date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date issueDate;
	
	@Column(nullable = false, name="return_date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date returnDate;
	
//	public EmployeeIssue() {
//		super();
//	}
//
//	public Long getIssueId() {
//		return issueId;
//	}
//
//	public void setIssueId(Long issueId) {
//		this.issueId = issueId;
//	}
//
//	public Employee getEmployee() {
//		return employee;
//	}
//
//	public void setEmployee(Employee employee) {
//		this.employee = employee;
//	}
//
//	public Item getItem() {
//		return item;
//	}
//
//	public void setItem(Item item) {
//		this.item = item;
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
//	public Date getReturnDate() {
//		return returnDate;
//	}
//
//	public void setReturnDate(Date returnDate) {
//		this.returnDate = returnDate;
//	}
//
//	public EmployeeIssue(Long issueId, Employee employee, Item item, Date issueDate, Date returnDate) {
//		super();
//		this.issueId = issueId;
//		this.employee = employee;
//		this.item = item;
//		this.issueDate = issueDate;
//		this.returnDate = returnDate;
//	}
//	
//	
	
}
