package com.onlinebanking.demo.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.onlinebanking.demo.entity.User;
import com.onlinebanking.demo.exceptions.InvalidException;
import com.onlinebanking.demo.exceptions.NotFoundException;
import com.onlinebanking.demo.exceptions.ResourceNotFound;
import com.onlinebanking.demo.service.UserServiceInterface;

@RestController
@RequestMapping("/banking")

public class UserController {
	@Autowired
	UserServiceInterface userService;
	 
    @GetMapping(path = "/userdetails", produces = {MediaType.APPLICATION_JSON_VALUE})
    List<User> users(){
        return userService.getUser();
    }
    
	
	@GetMapping("/user/{user_email}")
	User findByEmail(@PathVariable String user_email) throws ResourceNotFound
	{	User user= userService.getUserByEmail(user_email)
	.orElseThrow(() -> new ResourceNotFound("User not found for this id :: " + user_email));
       System.out.println(user_email);
    return user;
	}
	private boolean isValidEmail(String email)
	  {
		  return email.matches("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$");
	  }
	
	  @PostMapping("/user")
	    public ResponseEntity<Object> creatingUser(@Validated @RequestBody User newUser) throws InvalidException {
		  String email=newUser.getUser_email();
		  String password=newUser.getUser_pwd();
		  if(!isValidEmail(email))
		  {
			  throw new InvalidException("Invalid email");
		  }
		 
		
		  if(!isValidPassword(password))
		  {
			  throw new InvalidException("Password should be of atleast 8 characters");
		  }
		  
		 
	         userService.createUser(newUser);
	         return ResponseEntity.ok("{\"message\":\"User created successfully\"}");
		    
		  
	    }
	  
	  
	  
	  private boolean isValidPassword(String password) {
		  return password.length()>=8;
	
}
	  @PostMapping("/validate")
	  public ResponseEntity<Object> validateLogin(@RequestBody User loginReq)throws ResourceNotFound, NotFoundException, InvalidException
	  {
		  String email=loginReq.getUser_email();
		  String pwd=loginReq.getUser_pwd();
		  
		  if(email==null|| email.isEmpty())
			 throw new NotFoundException("Please enter the email");
		  
		  if(!isValidEmail(email))
		  {
			  throw new InvalidException("Invalid email");
		  }
		  if(pwd==null || pwd.isEmpty() ) 
	       {
			  throw new NotFoundException("Please enter the password");
	       }
		  
		  
			  
		  User user= userService.getUserByEmail(email)
					.orElseThrow(() -> new ResourceNotFound("User not found for this email :: " + email));
	       System.out.println(email);
	       
	       if(user==null)
	       {
	    	   throw new NotFoundException("User not found with the provided email");
	       }
	      
	       
	      
	       if(!(pwd.equals(user.getUser_pwd())))
	    		   {
	       throw new InvalidException("Invalid password");
	    		   }
	       return ResponseEntity.ok("{\"message\":\"Login Successful\"}");
	       
	       
	  }
}
