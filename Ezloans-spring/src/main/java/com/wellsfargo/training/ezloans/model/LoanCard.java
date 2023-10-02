package com.wellsfargo.training.ezloans.model;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "loan_card_master")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanCard {
	
	@Id
	@SequenceGenerator(name = "loan_seq",initialValue = 1064 , allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY , generator = "loan_seq")
	@Column(nullable = false, name="loan_id")
	private Long loanId;
	
	@Column(name="loan_type",nullable=false,unique = true)
	private String type;
	
	
	@Column(name="loan_duration",nullable=false)
	private int duration;

	/*
	 * @JsonManagedReference is the forward part of reference – the one that gets serialized normally.
	 * @JsonBackReference is the back part of reference – it will be omitted from serialization.
	 * @JsonIgnore shows you don't need to provide the object of empcard while creating loancard
	 */
	@OneToMany(mappedBy = "loanId" , cascade = CascadeType.ALL)
	@JsonBackReference
	@JsonIgnore
	private List<EmployeeCard> employeeCard;


	

}
