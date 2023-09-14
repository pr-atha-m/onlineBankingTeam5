package com.onlinebanking.demo.exceptions;

import java.util.Date;

public class ErrorResponse {
	private Date timestamp;
	 private String message;
	 private int status;

	 public ErrorResponse(Date timestamp, String message,int status) {
	  super();
	  this.timestamp = timestamp;
	  this.message = message;
	  this.status=status;
	 }

	 public Date getTimestamp() {
	  return timestamp;
	 }

	 public String getMessage() {
	  return message;
	 }

	 public int getStatus() {
	  return status;
	 }
	}