package com.onlinebanking.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


	
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public class ResourceNotFound extends Exception {
		private HttpStatus status;
		public ResourceNotFound(String message,HttpStatus status) {
			super(message);
			this.status=status;
		}
		public HttpStatus getStatus() {
			return status;
		}

	}