package com.wellsfargo.training.ezloans.model;

import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "loan_card_master")
public class LoanCard {
	
	@Id
	@SequenceGenerator(name = "loan_seq",initialValue = 1064 , allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY , generator = "loan_seq")
	@Column(nullable = false, name="loan_id")
	private Long loanId;
	
	@Column(name="loan_type",nullable=false)
	private String type;
	
	
	@Column(name="loan_duration",nullable=false)
	private int duration;

	@OneToMany(mappedBy = "loanId" , cascade = CascadeType.ALL)
	private Set<EmployeeCard> employeeCard;

	public LoanCard() {
		super();
	}

	public LoanCard(Long loanId, String type, int duration) {
		super();
		this.loanId = loanId;
		this.type = type;
		this.duration = duration;
	}

	public Long getLoanId() {
		return loanId;
	}

	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

}
