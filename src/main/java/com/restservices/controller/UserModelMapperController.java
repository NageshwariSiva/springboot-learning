package com.restservices.controller;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restservices.dto.UserDto;
import com.restservices.entities.User;
import com.restservices.exception.UserNotFoundException;
import com.restservices.services.UserService;

@RestController
@RequestMapping(value = "modelmapper/user")
@Validated
public class UserModelMapperController {
	
	@Autowired
	private UserService userservice;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/{id}")   
	public UserDto getUserByIDModelMapper(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException{ 
		
		Optional<User> userOptional = userservice.getUserByID(id);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("User Not Found");
		}
		User user = userOptional.get();
		//we are converting an entity object to DTO here. we use ModelMapper to covert it.
		//This DTO will define which fields of entity object will be displayed to Rest client
		UserDto dto = modelMapper.map(user,UserDto.class);
		return dto;
		
	}
}
