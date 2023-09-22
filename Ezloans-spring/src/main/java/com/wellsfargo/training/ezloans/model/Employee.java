package com.wellsfargo.training.ezloans.model;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Base64;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "employee_master")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Employee {

	@Id
	@SequenceGenerator(name = "employee_seq",initialValue = 100 , allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY , generator = "employee_seq")
	@Column(nullable = false, name="employee_id")
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
	@JsonBackReference
	@JsonIgnore
	private List<EmployeeIssue> employeeIssue;
	
	@OneToMany(mappedBy = "employee" , cascade = CascadeType.ALL)
	@JsonBackReference
	@JsonIgnore
	private List<EmployeeCard> employeeCard;
	

		

}
