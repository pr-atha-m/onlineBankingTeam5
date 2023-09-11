package com.onlinebanking.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<?>handleNotFoundException(NotFoundException ex){
		ErrorResponse errorResponse=new ErrorResponse(ex.getMessage());
		return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(InvalidException.class)
	public ResponseEntity<ErrorResponse>handleInvalidException(InvalidException ex){
		ErrorResponse errorResponse=new ErrorResponse(ex.getMessage());
		return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
	}

}
