package com.wellsfargo.training.ezloans.model;

import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "loan_card_master")
public class LoanCard {
	
	@Id
	private String load_id;
	
	@Column
	private String loan_type;
	
	
	@Column
	private int loan_duration;

	@OneToMany(mappedBy = "loan_id" , cascade = CascadeType.ALL)
	private Set<EmployeeCard> employeecard;

	public LoanCard() {
		super();
		// TODO Auto-generated constructor stub
	}


	public LoanCard(String load_id, String loan_type, int loan_duration) {
		super();
		this.load_id = load_id;
		this.loan_type = loan_type;
		this.loan_duration = loan_duration;
	}


	public String getLoad_id() {
		return load_id;
	}


	public void setLoad_id(String load_id) {
		this.load_id = load_id;
	}


	public String getLoan_type() {
		return loan_type;
	}


	public void setLoan_type(String loan_type) {
		this.loan_type = loan_type;
	}


	public int getLoan_duration() {
		return loan_duration;
	}


	public void setLoan_duration(int loan_duration) {
		this.loan_duration = loan_duration;
	}
	
	
}
