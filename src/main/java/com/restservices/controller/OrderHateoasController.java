package com.restservices.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restservices.entities.Order;
import com.restservices.entities.User;
import com.restservices.exception.UserNotFoundException;
import com.restservices.repository.OrderRepository;
import com.restservices.repository.UserRepository;

@RestController
@RequestMapping(value = "/orderhateoas")
@Validated
public class OrderHateoasController {
	
	@Autowired
	private UserRepository userrepo;
	
//	@Autowired
//	private OrderRepository orderrepo;
	
	@GetMapping("/{userid}")
	public List<Order> getAllOrders(@PathVariable("userid") Long id) throws Exception {
		
		Optional<User> optionaluser = userrepo.findById(id);
		if(!optionaluser.isPresent())
		{
			//throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User Not present In Repo");
			throw new UserNotFoundException("User Not Found");
		}
		return optionaluser.get().getOrders();
	}

}
