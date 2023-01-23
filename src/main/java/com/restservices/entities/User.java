package com.restservices.entities;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonView;

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
//@JsonIgnoreProperties({"fname", "laname"}) -- Static filtering
//@JsonFilter(value = "FilterName")
public class User {
	
	@Id	//This annotation says this variable is PRIMARY KAY/
	@GeneratedValue     //This annotation makes id to auto increment
	@JsonView(Views.External.class)
	private Long id;
	
	// A table should have one primarykey but can have multiple unique key.
	@NotEmpty(message = "Username should not be empty")
	@Column(name = "USER_NAME",length=50,nullable=false,unique=true)
	@JsonView(Views.External.class)
	private String username;   //unique key
	
	@Size(min=2,message="fname should length of atleast 2")
	@Column(name="FIRST_NAME",length=50,nullable=false)
	@JsonView(Views.External.class)
	private String fname;
	
	@Column(name="LAST_NAME",length=50,nullable=false)
	@JsonView(Views.External.class)
	private String lname;
	
	@Column(name="EMAIL",length=50,nullable=false)
	@JsonView(Views.External.class)
	private String email;
	
	@Column(name="ROLE",length=50,nullable=false)
	@JsonView(Views.Internal.class)
	private String role;
	
	@Column(name = "SSN",length=50,nullable=false,unique=true)
	//@JsonIgnore -- Static filtering
	@JsonView(Views.Internal.class)
	private String ssn;	//unique key

	@OneToMany(mappedBy = "user")
	@JsonView(Views.Internal.class)
	private List<Order> orders;
	
	@Column(name = "ADDRESS")
	private String address;
	
	
	//no argument constructor  (It is mandatory for JPA)
	public User() {
		
	}


	public User(Long id, @NotEmpty(message = "Username should not be empty") String username,
			@Size(min = 2, message = "fname should length of atleast 2") String fname, String lname, String email,
			String role, String ssn, List<Order> orders, String address) {
		super();
		this.id = id;
		this.username = username;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
		this.orders = orders;
		this.address = address;
	}

	
	
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


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", fname=" + fname + ", lname=" + lname + ", email="
				+ email + ", role=" + role + ", ssn=" + ssn + ", orders=" + orders + ", address=" + address + "]";
	}

	
}
