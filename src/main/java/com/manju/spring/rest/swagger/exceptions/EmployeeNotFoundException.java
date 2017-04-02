package com.manju.spring.rest.swagger.exceptions;

public class EmployeeNotFoundException extends RuntimeException {

	/**
	 * Generated Serial Version ID.
	 */
	private static final long serialVersionUID = -2182695050442347423L;
	
	
	public EmployeeNotFoundException(String message) {
		super(message);
	}

}
