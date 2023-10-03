package com.onlinebanking.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidException extends Exception {
	private HttpStatus status;
	public InvalidException(String message,HttpStatus status) {
		super(message);
		this.status=status;
	}
	public HttpStatus getStatus() {
		return status;
	}

}
