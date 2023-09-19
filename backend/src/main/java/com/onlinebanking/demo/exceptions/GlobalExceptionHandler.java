package com.onlinebanking.demo.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<?>HandleNotFoundException(NotFoundException ex){
		ErrorResponse errorResponse=new ErrorResponse(new Date(), ex.getMessage(),ex.getStatus().value());
		return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(InvalidException.class)
	public ResponseEntity<?>HandleInvalidException(InvalidException ex){
		ErrorResponse errorResponse=new ErrorResponse(new Date(), ex.getMessage(),ex.getStatus().value() );
		return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(BalanceExceptions.class)
	public ResponseEntity<?>HandleBalanceExceptions(BalanceExceptions ex){
		ErrorResponse errorResponse=new ErrorResponse(new Date(), ex.getMessage(),ex.getStatus().value() );
		return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
	}
	
	
}