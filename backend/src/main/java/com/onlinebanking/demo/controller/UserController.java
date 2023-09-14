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
import com.onlinebanking.demo.entity.Accounts;
import com.onlinebanking.demo.entity.User_account;
import com.onlinebanking.demo.exceptions.ResourceNotFound;
import com.onlinebanking.demo.repository.UserRepository;
import com.onlinebanking.demo.repository.AccountRepository;
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
    
	//Demo of @PathVariable
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
	
	  @PostMapping("/user/register")
	    public ResponseEntity<Object> creatingUser(@Validated @RequestBody User newUser) {
		 
	        User user= userService.createUser(newUser);
	        System.out.println(user.getFirst_name());
	        return ResponseEntity.ok(user);
	    }
	  
	  @PostMapping("/user/open")
	    public ResponseEntity<Object> creatingUser(@Validated @RequestBody User_account newUser) {
		 
	        User_account user= userService.createUserAccount(newUser);
	        
	        System.out.println(user.getUser_email());
	        return ResponseEntity.ok(user);
	    }
	  
	  @PostMapping("/user/createaccount")
	    public ResponseEntity<Object> creatingAccount(@Validated @RequestBody Accounts newAccount) {
		 
	        Accounts user= userService.createAccount(newAccount);
	        return ResponseEntity.ok(user);
	    }
	  
	  
//	  @PostMapping("/user/{id}/open")
//	  public ResponseEntity<Object> creatingUserAccount(@Validated @RequestBody User newUser) {
//			 
//	        User user= userService.createUser(newUser);
//	        System.out.println(user.getFirst_name());
//	        return ResponseEntity.ok(user);
//	  
//	  }
//	  
	  
	  
//	  private boolean isValidPassword(String password) {
//		  return password.length()>=8;
	
//}
	  @PostMapping("/validate")
	  public ResponseEntity<Object> validateLogin(@RequestBody User loginReq)throws ResourceNotFound
	  {
		  String email=loginReq.getUser_email();
		  String pwd=loginReq.getUser_pwd();
		  
		  if(email==null|| email.isEmpty())
			 return ResponseEntity.badRequest().body("{\"message\":\"Email is not provided\"}");
		  if(!isValidEmail(email))
		  {
			  return ResponseEntity.badRequest().body("{\"message\":\"Invalid email \"}");
		  }
			  
		  User user= userService.getUserByEmail(email)
					.orElseThrow(() -> new ResourceNotFound("User not found for this email :: " + email));
	       System.out.println(email);
	       
	       if(user==null)
	       {
	    	   return ResponseEntity.badRequest().body("{\"message\":\"Email is not found\"}");
	       }
	      
	       
	       if(pwd==null || !(pwd.equals(user.getUser_pwd())))
	       {
	    	   return ResponseEntity.badRequest().body("{\"message\":\"Password is incorrect\"}");
	       }
	       return ResponseEntity.ok("{\"message\":\"Login Successful\"}");
	       
	       
	  }
}
