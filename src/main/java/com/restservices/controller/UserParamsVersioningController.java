package com.restservices.controller;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restservices.dto.UserDtoV1;
import com.restservices.dto.UserDtoV2;
import com.restservices.entities.User;
import com.restservices.exception.UserNotFoundException;
import com.restservices.services.UserService;

@RestController
@RequestMapping(value = "versioning/params/user")
public class UserParamsVersioningController {
	
	@Autowired
	private UserService userservice;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping(value ="/{id}", params = "VERSION=1")   
	public UserDtoV1 getUserByIDModelMapperV1(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException{ 
		
		Optional<User> userOptional = userservice.getUserByID(id);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("User Not Found");
		}
		User user = userOptional.get();
		UserDtoV1 dtoV1 = modelMapper.map(user,UserDtoV1.class);
		return dtoV1;
		
	}
	
	@GetMapping(value ="/{id}", params = "VERSION=2")   
	public UserDtoV2 getUserByIDModelMapperV2(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException{ 
		
		Optional<User> userOptional = userservice.getUserByID(id);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("User Not Found");
		}
		User user = userOptional.get();
		UserDtoV2 dtoV2 = modelMapper.map(user,UserDtoV2.class);
		return dtoV2;
		
	}
}
