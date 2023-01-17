package com.restservices.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloworldController {
	
	////Internationalization
	@Autowired
	private ResourceBundleMessageSource messageSource;

	
//	URI and HTTP Methos is needed to connect restCliesnt/postman and restservise
//	@RequestMapping(method = RequestMethod.GET,path="/hello")
	@GetMapping("/hello")
	public String hello() {
		return "Hello!";
	}
	
	@GetMapping("/userhelo")
	public UserDetails helloBean()
	{
		return new UserDetails("jegadha","siva","theni");
	}
	
	
	//Example 1 is complex way 2 is easy way. both will give same output
	//Internationalization - example -1
	@GetMapping("/hello-int")
	public String getMessagesInI18NFormat(@RequestHeader(name = "Accept-Language", required=false) 
	String locale) {
		return messageSource.getMessage("label.hello", null, new Locale(locale));
		
	}
	
	//Internationalization - example -2 
	@GetMapping("/hello-int2")
	public String getMessagesInI18NFormat2() {
		return messageSource.getMessage("label.hello", null, LocaleContextHolder.getLocale());
		
	}

}
