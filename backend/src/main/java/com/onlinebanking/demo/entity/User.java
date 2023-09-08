package com.onlinebanking.demo.entity;

import jakarta.persistence.*;




@Entity
@Table(name = "User")
public class User {
	
	private String user_name;   
	private String user_email;
	
	private String user_pwd;
	
	public User() {
		super();
	}
	
	public User(int userid, String user_name, String user_email, String user_pwd) {
		super();
		
		this.user_name = user_name;
		this.user_email = user_email;
		this.user_pwd = user_pwd;
	}

	
	
	@Column(name = "user_name", nullable = false)
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	@Id
	@Column(name = "user_email", nullable = false)
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	
	@Column(name = "password", nullable = false)
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}

	
	
}