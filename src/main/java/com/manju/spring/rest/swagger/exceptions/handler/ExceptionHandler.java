package com.manju.spring.rest.swagger.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.manju.spring.rest.swagger.exceptions.EmployeeNotFoundException;
import com.manju.spring.rest.swagger.model.ErrorDto;

@ControllerAdvice
public class ExceptionHandler {
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value = EmployeeNotFoundException.class)
	public ResponseEntity<ErrorDto> handleEmployeeNotFoundException(EmployeeNotFoundException e) {
		ErrorDto errorDto  = new ErrorDto();
		errorDto.setErrorCode(HttpStatus.NOT_FOUND.toString());
		errorDto.setErrorMessage(e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		ErrorDto errorDto  = new ErrorDto();
		errorDto.setErrorCode(HttpStatus.BAD_REQUEST.toString());
		errorDto.setErrorMessage(e.getBindingResult().getFieldError().getDefaultMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
	}
	
}
