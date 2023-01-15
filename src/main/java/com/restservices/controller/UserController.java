package com.restservices.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.restservices.entities.User;
import com.restservices.exception.UserAlreadyExsistException;
import com.restservices.exception.UserNotFoundException;
import com.restservices.services.UserService;

@RestController
public class UserController {
	
	//Autowire Service
	@Autowired
	private UserService userservice;
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userservice.getAllUsers();
	}
	
	//<Void> -- V is caps since we need to use wrapper calss
	//UriComponentsBuilder--used to build Rest Client URI/Endpoint
	@PostMapping("/createuser")
	public ResponseEntity<Void> createUser(@RequestBody User user,UriComponentsBuilder builder)
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
	
	@GetMapping("/getuser/{id}")   //both id var should have same name
	public Optional<User> getUserByID(@PathVariable("id") Long id){
		
		try {
			return userservice.getUserByID(id);
		}catch(UserNotFoundException ex) {
			//ResponseStatusException -- hhtpstatus and error message is parameters
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
		}
	}
	
	@PutMapping("/updateuser/{id}")
	public User updateUserByID(@PathVariable("id") Long id, @RequestBody User user)
	{
		try {
		return userservice.updateUserByID(id, user);
		}catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
		}
	}
	
	@DeleteMapping("deleteuser/{id}")
	public void deleteUserByID(@PathVariable("id") Long id) {
		userservice.deleteUserByID(id);
	}
	
	@GetMapping("/findbyusername/username")
	public User findByUserName(@PathVariable("username") String username) {
		return userservice.findByUserName(username);
	}
	
	

}
