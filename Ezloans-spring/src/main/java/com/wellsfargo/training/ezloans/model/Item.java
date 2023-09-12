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
	
	@Column(nullable = false)
	private String item_desc;
	
	@Column(nullable = false)
	private String item_status;
	
	@Column(nullable = false)
	private String item_make;
	
	@Column(nullable = false)
	private String item_category;
	
	@Column(nullable = false)
	private long item_valuation;
	
	
	
	@OneToMany(mappedBy = "item" , cascade = CascadeType.ALL)
	private Set<EmployeeIssue> employeeissue;

	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Item(Long itemId, String item_desc, String item_status, String item_make, String item_category,
			long item_valuation) {
		super();
		this.itemId = itemId;
		this.item_desc = item_desc;
		this.item_status = item_status;
		this.item_make = item_make;
		this.item_category = item_category;
		this.item_valuation = item_valuation;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getItem_desc() {
		return item_desc;
	}

	public void setItem_desc(String item_desc) {
		this.item_desc = item_desc;
	}

	public String getItem_status() {
		return item_status;
	}

	public void setItem_status(String item_status) {
		this.item_status = item_status;
	}

	public String getItem_make() {
		return item_make;
	}

	public void setItem_make(String item_make) {
		this.item_make = item_make;
	}

	public String getItem_category() {
		return item_category;
	}

	public void setItem_category(String item_category) {
		this.item_category = item_category;
	}

	public long getItem_valuation() {
		return item_valuation;
	}

	public void setItem_valuation(long item_valuation) {
		this.item_valuation = item_valuation;
	}
	
	

}
