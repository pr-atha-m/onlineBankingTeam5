package com.onlinebanking.demo.entity;


public class AuthResponse {
	
	private String token;
	private String email;
	
	
	
	public AuthResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuthResponse(String token, String email) {
		super();
		this.token = token;
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public static AuthResponseBuilder builder()
	{
		return new AuthResponseBuilder();
	}
	
	public static class AuthResponseBuilder
	{
		private AuthResponse auth = new AuthResponse();
		
		public AuthResponseBuilder token (String token)
		{
			auth.setToken(token);
			return this;
		}
		
		public AuthResponseBuilder email (String email)
		{
			auth.setEmail(email);
			return this;
		}
		
		public AuthResponse build()
		{
			return auth;
		}
	}
	
	
}