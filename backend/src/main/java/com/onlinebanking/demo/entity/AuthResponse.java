package com.onlinebanking.demo.entity;

public class AuthResponse {
	
	private String user_email;
	private boolean isValid;
	
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public boolean isValid() {
		return isValid;
	}
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	public AuthResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AuthResponse(String user_email, boolean isValid) {
		super();
		this.user_email = user_email;
		this.isValid = isValid;
	}

	
}
