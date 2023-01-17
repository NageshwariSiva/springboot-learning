package com.restservices.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.restservices.entities.Order;
import com.restservices.entities.User;
import com.restservices.exception.UserNotFoundException;
import com.restservices.repository.OrderRepository;
import com.restservices.repository.UserRepository;

@RestController
@RequestMapping(value = "order")
public class OrderController {
	
	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private OrderRepository orderrepo;
	
	
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
	
	@PostMapping("/{userid}")
	public Order CreateOrder(@PathVariable("userid") Long id,@RequestBody Order order) {
		Optional<User> optionaluser = userrepo.findById(id);
		if(!optionaluser.isPresent())
		{
			//throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User Not present In Repo");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User Not Found");
		}
		User user = optionaluser.get();
		order.setUser(user);
		return orderrepo.save(order);
	}
	
	
	@GetMapping("/{userid}/{orderid}")
	public Order GetOrderByOrderID(@PathVariable("userid")Long id, @PathVariable("orderid") Long orderid ) {
		Optional<User> optionaluser = userrepo.findById(id);
		Optional<Order> optionalorder = orderrepo.findById(orderid);
		if(!optionaluser.isPresent() || !optionalorder.isPresent())
		{
			//throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User Not present In Repo");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User/Order Not Found");
		}
		return optionalorder.get();
		
	}

}
