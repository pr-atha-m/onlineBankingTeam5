package com.onlinebanking.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends Exception {
	private HttpStatus status;
	public NotFoundException(String message,HttpStatus status) {
		super(message);
		this.status=status;
	}
	public HttpStatus getStatus() {
		return status;
	}
	
	}
	



