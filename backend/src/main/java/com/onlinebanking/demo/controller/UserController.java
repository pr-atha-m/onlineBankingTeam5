package com.onlinebanking.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinebanking.demo.model.User;

@RestController
@RequestMapping("/user")

public class UserController {
	User user;
	@GetMapping("{userID}")
	public User getUser(String userID)
	{
		return new User( "A1", "Priya", "9999999999");
	}
	
	@PostMapping
	public String createUser(@RequestBody User user)
	{
		this.user=user;
		return "User created successfully";
	}

}
