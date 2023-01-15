package com.restservices.exception;

public class UserNotFoundException extends Exception{

	
	private static final long serialVersionUID = 1L;
	
	
	//constructor with string type
	public UserNotFoundException(String message) {
		super(message);
	}
	
	

}
