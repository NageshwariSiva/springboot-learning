package com.restservices.entities;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

// @Entity(name ="user") -- we can specifically name the entity..
//otherwise class name will act as entity name
//@Table(name ="user",schema = "usermgmt") -- If in DB mutiple tables have same name
//but with different schema, we can send schema to uniquely identify table.
@Entity
@Table(name = "users")
@JsonIgnoreProperties({"firstname", "lastname"})
public class User {
	
	@Id	//This annotation says this variable is PRIMARY KAY/
	@GeneratedValue     //This annotation makes id to auto increment
	private Long id;
	
	// A table should have one primarykey but can have multiple unique key.
	@NotEmpty(message = "Username should not be empty")
	@Column(name = "USER_NAME",length=50,nullable=false,unique=true)
	private String username;   //unique key
	
	@Size(min=2,message="fname should length of atleast 2")
	@Column(name="FIRST_NAME",length=50,nullable=false)
	private String fname;
	
	@Column(name="LAST_NAME",length=50,nullable=false)
	private String lname;
	
	@Column(name="EMAIL",length=50,nullable=false)
	private String email;
	
	@Column(name="ROLE",length=50,nullable=false)
	private String role;
	
	@Column(name = "SSN",length=50,nullable=false,unique=true)
	@JsonIgnore
	private String ssn;	//unique key

	@OneToMany(mappedBy = "user")
	private List<Order> orders;
	
	//no argument constructor  (It is mandatory for JPA)
	public User() {
		
	}

	//argument constructor
	public User(Long id, String username, String fname, String lname, String email, String role, String ssn) {
		super();
		this.id = id;
		this.username = username;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
	}

	//getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	//Tostring
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", fname=" + fname + ", lname=" + lname + ", email="
				+ email + ", role=" + role + ", ssn=" + ssn + "]";
	}
	
}
