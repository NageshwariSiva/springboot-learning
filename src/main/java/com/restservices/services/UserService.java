package com.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.restservices.entities.User;
import com.restservices.exception.UserAlreadyExsistException;
import com.restservices.exception.UserNotFoundException;
import com.restservices.repository.UserRepository;

@Service
public class UserService {
	
	//Autowire Repository
	@Autowired
	private UserRepository userrepo;
	
	public List<User> getAllUsers(){
		return userrepo.findAll();
	}
	
	
	public User createUser(User user) throws UserAlreadyExsistException {
		//username unique constraint. So if input username already exsist then Exception 
		User userPresent = userrepo.findByUsername(user.getUsername());
		if(userPresent != null) {
			throw new UserAlreadyExsistException("User already Exsists in Repo");
		}
		return userrepo.save(user);
	}
	
	public Optional<User> getUserByID(Long id) throws UserNotFoundException{  // incase given ID not presetn in db. user object will be null. so we should declare it as optional
		Optional<User> user = userrepo.findById(id);
		
		if(!user.isPresent()) {
			throw new UserNotFoundException("User Not Found in Repository");
		}
		return user;
	}
	
	public User updateUserByID(Long id, User user)throws UserNotFoundException {
		Optional<User> optionalUser = userrepo.findById(id);
		
		if(!optionalUser.isPresent()) {
			throw new UserNotFoundException("User Not Found in Repository,Check the User ID");
		}
		user.setId(id);
		return userrepo.save(user);
	}
	
	//Here directly throwing exception from service layer itself
	public void deleteUserByID(Long id) {
		Optional<User> optionalUser = userrepo.findById(id);
		
		if(!optionalUser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User Not Found in Repository,Check the User ID");
		}
		userrepo.deleteById(id);
	}
	
	public User findByUserName(String username) {
		return userrepo.findByUsername(username);
	}
	
	
}
