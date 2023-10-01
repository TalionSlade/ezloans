package com.wellsfargo.training.ezloans.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
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
	
	//Many To One Mapping
	@ManyToOne
	@JoinColumn(nullable=false,name = "eid")
	@JsonManagedReference
	private Employee employee;
	
	//Many To One Mapping
	@ManyToOne
	@JoinColumn(nullable=false,name = "loanId")
	@JsonManagedReference
	private LoanCard loanId;
	
	
	@Column(nullable=false,name="card_issue_date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date issueDate;

	
	
}
