package com.onlinebanking.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*@Data
@AllArgsConstructor
@NoArgsConstructor*/


public class AuthRequest {
	
	 private String user_email;
	 private String user_pwd;
	 public AuthRequest(String user_email, String user_pwd) {
			super();
			this.user_email = user_email;
			this.user_pwd = user_pwd;
		}
	 
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
		
	  

}
