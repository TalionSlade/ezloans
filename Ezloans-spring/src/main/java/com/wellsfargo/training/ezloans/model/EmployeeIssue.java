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

	/*
     * Modeling with foreign key relationship in JPA.
     * Place @OneToOne on the primary class entity field Dealer.
     * Place @JoinColumn to configure foreign key column dealer_id in the Address class 
     * that maps to the primary key column of Dealer class. 
     */
	@ManyToOne
	@JoinColumn(nullable=false,name = "eid")
	@JsonManagedReference
	private Employee employee;
	
	/*
	 * @JsonManagedReference is the forward part of reference – the one that gets serialized normally.
	 * @JsonBackReference is the back part of reference – it will be omitted from serialization.
	 * @JsonIgnore shows you don't need to provide the object of empcard while creating loancard
	 */
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

	
}
