package com.restservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloworldController {
	
//	URI and HTTP Methos is needed to connect restCliesnt/postman and restservise
//	@RequestMapping(method = RequestMethod.GET,path="/hello")
	@GetMapping("/hello")
	public String hello() {
		return "Hello!";
	}
	
	@GetMapping("/user")
	public UserDetails helloBean()
	{
		return new UserDetails("jegadha","siva","theni");
	}

}
