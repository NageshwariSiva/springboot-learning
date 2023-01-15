package com.restservices.exception;


import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice                                       // either @RestControllerAdvice or @ControllerAdvice should be used.. so commenting for now
public class GlobalRestControllerAdviceExceptionHandler {
	
	@ExceptionHandler(UserNameNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND) // whenever notfound HTTP status is coming as outcome this function will be triggered
	public CustomErrorDetails usernameNotFound(UserNameNotFoundException ex) {
		
		return new CustomErrorDetails(new Date(), "From @RestControllerAdvice NOT FOUND", 
				ex.getMessage());
	}

}
