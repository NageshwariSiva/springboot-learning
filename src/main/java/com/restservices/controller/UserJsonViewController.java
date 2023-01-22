package com.restservices.controller;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;
import com.restservices.entities.User;
import com.restservices.entities.Views;
import com.restservices.entities.Views.External;
import com.restservices.exception.UserNotFoundException;
import com.restservices.services.UserService;

@RestController
@Validated
@RequestMapping(value = "/jsonview/user")
public class UserJsonViewController {
	
	@Autowired
	private UserService userservice;
	
	@GetMapping("/external/{id}")  
	@JsonView(External.class)
	public Optional<User> getUserByIDExternal(@PathVariable("id") @Min(1) Long id){ // min annotation -- ensuring whether user added id value in path
		
		try {
			return userservice.getUserByID(id);
		}catch(UserNotFoundException ex) {
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
		}
	}
	
	@GetMapping("/internal/{id}")   
	@JsonView(Views.Internal.class)
	public Optional<User> getUserByIDInternal(@PathVariable("id") @Min(1) Long id){ // min annotation -- ensuring whether user added id value in path
		
		try {
			return userservice.getUserByID(id);
		}catch(UserNotFoundException ex) {
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
		}
	}
}
