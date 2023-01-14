package com.restservices.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restservices.entities.User;
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
	
	@PostMapping("/createuser")
	public User createUser(@RequestBody User user)
	{
		return userservice.createUser(user);
	}
	
	@GetMapping("/getuser/{id}")   //both id var should have same name
	public Optional<User> getUserByID(@PathVariable("id") Long id){
		return userservice.getUserByID(id);
	}
	
	@PutMapping("/updateuser/{id}")
	public User updateUserByID(@PathVariable("id") Long id, @RequestBody User user)
	{
		return userservice.updateUserByID(id, user);
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
