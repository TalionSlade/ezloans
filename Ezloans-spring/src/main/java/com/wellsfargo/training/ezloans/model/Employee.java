package com.wellsfargo.training.ezloans.model;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.Base64;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee_master")
public class Employee {

	
	@Id
	@SequenceGenerator(name = "employee_seq",initialValue = 100 , allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY , generator = "employee_seq")
	private Long employee_Id;
	
	
	@Column(nullable = false)
	private String employee_name;
	
	@Column(nullable = false)
	private String employee_password;
	
	@Column(nullable = false)
	private String employee_designation;
	
	@Column(nullable = false)
	private String employee_department;
	
	@Column(nullable = false)
	private String employee_gender;
	
	@Column(nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date employee_dob;
	
	
	@Column(nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date employee_doj;

	@OneToMany(mappedBy = "employee" , cascade = CascadeType.ALL)
	private Set<EmployeeIssue> employeeissue;
		
//	private EmployeeIssue employeeissue;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Employee(Long employee_Id, String employee_name,String employee_password, String employee_designation, String employee_department,
			String employee_gender, Date employee_dob, Date employee_doj) {
		super();
		this.employee_Id = employee_Id;
		this.employee_name = employee_name;
		this.employee_designation = employee_designation;
		this.employee_department = employee_department;
		this.employee_gender = employee_gender;
		this.employee_dob = employee_dob;
		this.employee_doj = employee_doj;
		this.employee_password=employee_password;
	}


	public Long getEmployee_Id() {
		return employee_Id;
	}


	public void setEmployee_Id(Long employee_Id) {
		this.employee_Id = employee_Id;
	}


	public String getEmployee_name() {
		return employee_name;
	}


	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	
	public String getEmployee_password() {
		return employee_password;
	}


	public void setEmployee_password(String password) {
		Base64.Encoder encoder = Base64.getEncoder();  
        String normalString = password;
        String encodedString = encoder.encodeToString(   // encrypt password in database field
        normalString.getBytes(StandardCharsets.UTF_8) );
		this.employee_password = encodedString ;
	}


	public String getEmployee_designation() {
		return employee_designation;
	}


	public void setEmployee_designation(String employee_designation) {
		this.employee_designation = employee_designation;
	}


	public String getEmployee_department() {
		return employee_department;
	}


	public void setEmployee_department(String employee_department) {
		this.employee_department = employee_department;
	}


	public String getEmployee_gender() {
		return employee_gender;
	}


	public void setEmployee_gender(String employee_gender) {
		this.employee_gender = employee_gender;
	}


	public Date getEmployee_dob() {
		return employee_dob;
	}


	public void setEmployee_dob(Date employee_dob) {
		this.employee_dob = employee_dob;
	}


	public Date getEmployee_doj() {
		return employee_doj;
	}


	public void setEmployee_doj(Date employee_doj) {
		this.employee_doj = employee_doj;
	}
	
	
}
