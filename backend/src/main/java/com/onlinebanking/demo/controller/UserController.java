package com.onlinebanking.demo.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.onlinebanking.demo.entity.User;
import com.onlinebanking.demo.exceptions.ResourceNotFound;
import com.onlinebanking.demo.service.UserServiceInterface;

@RestController
@RequestMapping("/api")

public class UserController {
	@Autowired
	UserServiceInterface userService;
	 //@GetMapping(path = "/products", produces = {MediaType.APPLICATION_XML_VALUE})
    @GetMapping(path = "/userdetails", produces = {MediaType.APPLICATION_JSON_VALUE})
    List<User> users(){
        return userService.getUser();
    }
    
	//Demo of @PathVariable
	@GetMapping("/user/{id}")
	User findByEmail(@PathVariable String user_email) throws ResourceNotFound
	{	User user= userService.getUserByEmail(user_email)
	.orElseThrow(() -> new ResourceNotFound("User not found for this id :: " + user_email));
       System.out.println(user_email);
    return user;
	}
	
	  @PostMapping("/user")
	    public User creatingUser(@Validated @RequestBody User newUser) {
	        return userService.createUser(newUser);
	    }
	
}
