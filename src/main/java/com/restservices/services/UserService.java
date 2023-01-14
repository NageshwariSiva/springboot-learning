package com.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restservices.entities.User;
import com.restservices.repository.UserRepository;

@Service
public class UserService {
	
	//Autowire Repository
	@Autowired
	private UserRepository userrepo;
	
	public List<User> getAllUsers(){
		return userrepo.findAll();
	}
	
	public User createUser(User user) {
		return userrepo.save(user);
	}
	
	public Optional<User> getUserByID(Long id) {  // incase given ID not presetn in db. user object will be null. so we should declare it as optional
		Optional<User> user = userrepo.findById(id);
		return user;
	}
	
	public User updateUserByID(Long id, User user) {
		user.setId(id);
		return userrepo.save(user);
	}
	
	public void deleteUserByID(Long id) {
		if(userrepo.findById(id).isPresent()) {
			userrepo.deleteById(id);
		}
	}
	
	public User findByUserName(String username) {
		return userrepo.findByUsername(username);
	}
	
	
}
