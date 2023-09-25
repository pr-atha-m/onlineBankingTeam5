package com.onlinebanking.demo.entity;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;




@Entity
@Table(name = "User")
public class User implements UserDetails {
	
	private String user_email;
	private String user_pwd;
	private String first_name;
	private String last_name;
	
	//This implies that 1 user can have many accounts.
//	@OneToMany(cascade=CascadeType.ALL)
//	private Set<Account> accounts;
	
	public User() {
		super();
	}
	
	public User(String first_name, String last_name, String user_email, String user_pwd) {
		super();
		
		this.first_name = first_name;
		this.user_email = user_email;
		this.user_pwd = user_pwd;
		this.last_name=last_name;
	}

	
	
//	public Set<Account> getAccounts() {
//		return accounts;
//	}
//	public void setAccounts(Set<Account> accounts) {
//		this.accounts = accounts;
//	}
	
	
	@Id
	@Column(name = "user_email", nullable = false)
	@NotEmpty(message="You need to submit the email id")
	@Email(message="This is not a valid email format")
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	
	@Column(name = "password", nullable = false)
	@NotEmpty(message="Password cannot be blank")
	@Size(min=8,max=16,message="The password needs to be between 8 and 16 characters")
	@Pattern(regexp= " ^(?=.*[0-9]) (?=.*[A-Z]) (?=.*[@#$%^&+=!])", message="Password should contain atleast 1 digit, 1 capital letter, 1 special character ")
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	
	@Column(name = "first_name", nullable = false)
	@NotEmpty(message="First name cannot be empty")
	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	
	@Column(name = "last_name", nullable = false)
	@NotEmpty(message="last name cannot be empty")
	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.user_email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}