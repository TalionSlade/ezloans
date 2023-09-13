package com.wellsfargo.training.ezloans.model;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "item_master")
public class Item {
	
	@Id
	@SequenceGenerator(name = "item_seq",initialValue = 100 , allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY , generator = "item_seq")
	private Long itemId;
	
	@Column(nullable = false, name="item_desc")
	private String desc;
	
	@Column(nullable = false, name="item_status")
	private String status;
	
	@Column(nullable = false, name="item_make")
	private String make;
	
	@Column(nullable = false, name="item_category")
	private String category;
	
	@Column(nullable = false, name="item_valuation")
	private long valuation;
	
	@OneToMany(mappedBy = "item" , cascade = CascadeType.ALL)
	private Set<EmployeeIssue> employeeIssue;

	public Item() {
		super();
	}

	public Item(Long itemId, String desc, String status, String make, String category, long valuation) {
		super();
		this.itemId = itemId;
		this.desc = desc;
		this.status = status;
		this.make = make;
		this.category = category;
		this.valuation = valuation;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getValuation() {
		return valuation;
	}

	public void setValuation(long valuation) {
		this.valuation = valuation;
	}

	
}
