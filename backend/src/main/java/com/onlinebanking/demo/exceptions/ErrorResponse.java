package com.onlinebanking.demo.exceptions;

public class ErrorResponse {
	private String message;
	private int status;
	
	public ErrorResponse(String message) {
		this.message=message;
		this.status=status;
	}
	
	public String getMessage() {
		return message;
	}
 public void setMessage(String message) {
	 this.message=message;
 }
 

}
