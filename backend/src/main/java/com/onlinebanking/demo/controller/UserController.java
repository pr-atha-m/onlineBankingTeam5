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
import com.onlinebanking.demo.repository.UserRepository;
import com.onlinebanking.demo.service.UserServiceInterface;

@RestController
@RequestMapping("/banking")

public class UserController {
	@Autowired
	UserServiceInterface userService;
	 //@GetMapping(path = "/products", produces = {MediaType.APPLICATION_XML_VALUE})
    @GetMapping(path = "/userdetails", produces = {MediaType.APPLICATION_JSON_VALUE})
    List<User> users(){
        return userService.getUser();
    }
    
	//Demo of @PathVariable
	@GetMapping("/user/{user_email}")
	User findByEmail(@PathVariable String user_email) throws ResourceNotFound
	{	User user= userService.getUserByEmail(user_email)
	.orElseThrow(() -> new ResourceNotFound("User not found for this id :: " + user_email));
       System.out.println(user_email);
    return user;
	}
	
	  @PostMapping("/user")
	    public String creatingUser(@Validated @RequestBody User newUser) {
		  String email=newUser.getUser_email();
		  String password=newUser.getUser_pwd();
		  
		 if(!isValidEmail(email) && !isValidPassword(password) )
		  {
			  return "Invalid email and password";
		  }
		  else if(!isValidPassword(password))
		  {
			  return "Invalid password";
		  }
		  else if(!isValidEmail(email))
		  {
			  return "Invalid email ";
		  }
		 
		  else {
	         userService.createUser(newUser);
	         return "User created successfully";
		  }
		  
		  
		  
	    }
	  
	  private boolean isValidEmail(String email)
	  {
		  return email.matches("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$");
	  }
	  
	  private boolean isValidPassword(String password) {
		  return password.length()>=8;
	
}
	  @PostMapping("/validate")
	  public String validateLogin(@RequestBody User loginReq)throws ResourceNotFound
	  {
		  String email=loginReq.getUser_email();
		  String pwd=loginReq.getUser_pwd();
		  
		  if(email==null|| email.isEmpty())
			  return "Email not provided";
			  
		  User user= userService.getUserByEmail(email)
					.orElseThrow(() -> new ResourceNotFound("User not found for this email :: " + email));
	       System.out.println(email);
	       
	       if(user==null)
	       {
	    	   return "email not found";
	       }
	      
	       
	       if(pwd==null || !(pwd.equals(user.getUser_pwd())))
	       {
	    	   return "Invalid Password";
	       }
	       return "login successful";
	  }
}
