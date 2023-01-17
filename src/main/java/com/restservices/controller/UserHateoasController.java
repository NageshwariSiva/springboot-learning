package com.restservices.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.restservices.entities.User;
import com.restservices.exception.UserNotFoundException;
import com.restservices.services.UserService;

@RestController
@RequestMapping(value = "/userhateoas")
@Validated
public class UserHateoasController {
	
//	@Autowired
//	private UserRepository repo;
	
	@Autowired
	private UserService userservice;
	
	@GetMapping
	public List<User> getAllUsers(){
		List<User> allUsers = userservice.getAllUsers();
		
		//Adding Self link to Each user
		for(User user: allUsers) {
			Long userid = user.getId();
			Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
			user.add(selfLink);
		}
		//CollectionModel<User> result = new CollectionModel<>(allUsers);
		//return result;
		return allUsers;
	}
	

	@GetMapping("/{id}")   //both id var should have same name
	public User getUserByID(@PathVariable("id") @Min(1) Long id){ // min annotation -- ensuring whether user added id value in path
		// error should come. chnage the return type to CollectionModel<User>
		try {
			Optional<User> optionaluser = userservice.getUserByID(id);
			User user = optionaluser.get();
			Long userId = user.getId();
			Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userId).withSelfRel();
			user.add(selfLink);
			//RepresentationModel model = new RepresentationModel(user);
			return user; 
		}catch(UserNotFoundException ex) {
			//ResponseStatusException -- httpstatus and error message is parameters
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
		}
	}
	
	

}
