package com.restservices.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.restservices.entities.User;
import com.restservices.exception.UserAlreadyExsistException;
import com.restservices.exception.UserNotFoundException;
import com.restservices.services.UserService;

@RestController
@Validated
@RequestMapping(value = "/user")
public class UserController {
	
	//Autowire Service
	@Autowired
	private UserService userservice;
	
	@GetMapping
	public List<User> getAllUsers(){
		return userservice.getAllUsers();
	}
	
	//<Void> -- V is caps since we need to use wrapper calss
	//UriComponentsBuilder--used to build Rest Client URI/Endpoint
	//@Valid - When we use this annotation, the input value will be validated across what condition we give in entity calss fields
	@PostMapping
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user,UriComponentsBuilder builder)
	{
		try {
			userservice.createUser(user);
			HttpHeaders header = new HttpHeaders();
			//setting location header
			//creating client endpoint -- test in postman for clear understanding or google it
			header.setLocation(builder.path("/getuser/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<Void>(header,HttpStatus.BAD_REQUEST);
			
		}catch(UserAlreadyExsistException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
		}
	}
	
	@GetMapping("/{id}")   //both id var should have same name
	public Optional<User> getUserByID(@PathVariable("id") @Min(1) Long id){ // min annotation -- ensuring whether user added id value in path
		
		try {
			return userservice.getUserByID(id);
		}catch(UserNotFoundException ex) {
			//ResponseStatusException -- hhtpstatus and error message is parameters
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public User updateUserByID(@PathVariable("id") Long id, @RequestBody User user)
	{
		try {
		return userservice.updateUserByID(id, user);
		}catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public void deleteUserByID(@PathVariable("id") Long id) {
		userservice.deleteUserByID(id);
	}
	
	@GetMapping("/findbyusername/username")
	public User findByUserName(@PathVariable("username") String username) throws UserNotFoundException {
		User user = userservice.findByUserName(username);
		if(user == null) {
			throw new UserNotFoundException("Given user not exsist in Repository");
		}
		return user;
	}
	
	

}
