package com.restservices.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.restservices.entities.User;
import com.restservices.exception.UserNotFoundException;
import com.restservices.services.UserService;

@RestController
@RequestMapping(value = "/Jackson/user")
@Validated
public class UserMappingJacksonController {
	
	//Autowire Service
		@Autowired
		private UserService userservice;
	
	
	@GetMapping("/{id}")   //both id var should have same name
	public MappingJacksonValue getUserByID(@PathVariable("id") @Min(1) Long id){ // min annotation -- ensuring whether user added id value in path
		
		try {
			
			Optional<User> optionalUser =  userservice.getUserByID(id);
			User user = optionalUser.get();
			
			Set<String> filter = new HashSet<String>();
			filter.add("id");
			filter.add("username");
			filter.add("ssn");
			FilterProvider filterProvider = new SimpleFilterProvider().addFilter("FilterName",
					SimpleBeanPropertyFilter.filterOutAllExcept(filter));
			MappingJacksonValue mapper = new MappingJacksonValue(user);

			mapper.setFilters(filterProvider);
			return mapper;	
			
		}catch(UserNotFoundException ex) {
			//ResponseStatusException -- hhtpstatus and error message is parameters
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
		}
	}
	
	@GetMapping("/params/{id}")   //both id var should have same name
	public MappingJacksonValue getUserByIDDynamic(@PathVariable("id") @Min(1) Long id,
			@RequestParam Set<String> filter){ // min annotation -- ensuring whether user added id value in path
		
		try {
			
			Optional<User> optionalUser =  userservice.getUserByID(id);
			User user = optionalUser.get();
			FilterProvider filterProvider = new SimpleFilterProvider().addFilter("FilterName",
					SimpleBeanPropertyFilter.filterOutAllExcept(filter));
			MappingJacksonValue mapper = new MappingJacksonValue(user);

			mapper.setFilters(filterProvider);
			return mapper;	
			
		}catch(UserNotFoundException ex) {
			//ResponseStatusException -- hhtpstatus and error message is parameters
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
		}
	}
}
