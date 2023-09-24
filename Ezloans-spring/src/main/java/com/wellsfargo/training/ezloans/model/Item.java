package com.wellsfargo.training.ezloans.model;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "item_master")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {
	
	@Id
	@SequenceGenerator(name = "item_seq",initialValue = 100 , allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY , generator = "item_seq")
	private Long itemId;
	
	@Column(nullable = false, name="item_desc")
	private String desc;
	
	@Column( nullable = false, name="item_status")
	private String status;
	
	@Column(nullable = false, name="item_make")
	private String make;
	
	@Column(nullable = false, name="item_category")
	private String category;
	
	@Column( nullable = false, name="item_valuation")
	private long valuation;
	
	@OneToMany(mappedBy = "item" , cascade = CascadeType.ALL)
	@JsonBackReference
	@JsonIgnore
	private List<EmployeeIssue> employeeIssue;

	}
