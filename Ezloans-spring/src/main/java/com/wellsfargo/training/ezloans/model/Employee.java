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
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee_master")
public class Employee {

	@Id
	@SequenceGenerator(name = "employee_seq",initialValue = 100 , allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY , generator = "employee_seq")
	@Column(name="employee_id")
	private Long eid;
	
	@Column(nullable = false, name="first_name")
	private String fname;
	
	@Column(nullable = false, name="last_name")
	private String lname;
	
	@Column(nullable = false, name="employee_email")
	private String email;
	
	@Column(nullable = false, name="employee_password")
	private String password;
	
	@Column(nullable = false, name="designation")
	private String designation;
	
	@Column(nullable = false, name="department")
	private String department;
	
	@Column(nullable = false, name="gender")
	private String gender;
	
	@Column(nullable = false, name="date_of_birth")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dob;
	
	@Column(nullable = false, name="date_of_joining")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date doj;

	@OneToMany(mappedBy = "employee" , cascade = CascadeType.ALL)
	private Set<EmployeeIssue> employeeIssue;
		
	public Employee() {
		super();
	}

	public Employee(Long eid, String fname, String lname, String email, String password, String designation,
			String department, String gender, Date dob, Date doj) {
		super();
		this.eid = eid;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.password = password;
		this.designation = designation;
		this.department = department;
		this.gender = gender;
		this.dob = dob;
		this.doj = doj;
	}

	public Long getEid() {
		return eid;
	}

	public void setEid(Long eid) {
		this.eid = eid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		Base64.Encoder encoder = Base64.getEncoder();  
        String normalString = password;
        String encodedString = encoder.encodeToString(normalString.getBytes(StandardCharsets.UTF_8) );
        this.password = encodedString;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

}
