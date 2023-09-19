package com.wellsfargo.training.ezloans.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class EmployeeCardAndLoanCardProjection {
	
	private long cardId;
	private Long loanId;
	private Date issueDate;
	private String type;
	private int duration;
	
	
	
	

}
