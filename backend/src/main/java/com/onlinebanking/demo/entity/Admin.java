package com.onlinebanking.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="admin")
public class Admin {
	
	//public Long admin_id;
	public String admin_user;
	public String admin_email;
	public String admin_pwd;
	
	public Admin() {
		super();
	}
	
	public Admin(String admin_user, String admin_email, String admin_pwd) {
		super();
		//this.admin_id=admin_id;
		this.admin_user = admin_user;
		this.admin_email = admin_email;
		this.admin_pwd = admin_pwd;
	}
	
//	@Id
//	@GeneratedValue(strategy= GenerationType.IDENTITY)
//	@Column(name = "admin_id",nullable = false)
//	public Long getAdmin_id() {
//		return admin_id;
//	}
//
//	public void setAdmin_id(Long admin_id) {
//		this.admin_id = admin_id;
//	}

	@Column(name = "admin_username", nullable = false)
	@NotEmpty(message="username cannot be empty")
	public String getAdmin_user() {
		return admin_user;
	}
	public void setAdmin_user(String admin_user) {
		this.admin_user = admin_user;
	}
	
	@Id
	@Column(name = "admin_email", nullable = false)
	@NotEmpty(message="You need to submit the email id")
	@Email(message="This is not a valid email format")
	public String getAdmin_email() {
		return admin_email;
	}
	public void setAdmin_email(String admin_email) {
		this.admin_email = admin_email;
	}
	
	@Column(name = "admin_pwd", nullable = false)
	@NotEmpty(message="Password cannot be blank")
	@Size(min=8,max=16,message="The password needs to be between 8 and 16 characters")
	@Pattern(regexp= " ^(?=.*[0-9]) (?=.*[A-Z]) (?=.*[@#$%^&+=!])", message="Password should contain atleast 1 digit, 1 capital letter, 1 special character ")
	public String getAdmin_pwd() {
		return admin_pwd;
	}
	public void setAdmin_pwd(String admin_pwd) {
		this.admin_pwd = admin_pwd;
	}
	
	
	

}

