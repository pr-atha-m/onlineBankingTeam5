package com.onlinebanking.demo.controller;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.onlinebanking.demo.entity.User;

import com.onlinebanking.demo.entity.User_account;
import com.onlinebanking.demo.entity.User_account;
import com.onlinebanking.demo.exceptions.BalanceExceptions;
import com.onlinebanking.demo.exceptions.InvalidException;
import com.onlinebanking.demo.exceptions.NotFoundException;
import com.onlinebanking.demo.exceptions.ResourceNotFound;
import com.onlinebanking.demo.repository.UserRepository;
import com.onlinebanking.demo.repository.UserAccountRepository;

import com.onlinebanking.demo.service.UserServiceInterface;

@RestController
@RequestMapping("/banking")
@CrossOrigin(origins="http://localhost:8080")
public class UserController {
	
	

	
	@Autowired
	UserServiceInterface userService;
	
	
	 //To get the details of all users
    @GetMapping(path = "/userdetails", produces = {MediaType.APPLICATION_JSON_VALUE})
    List<User> users(){
        return userService.getUser();
    }
    
    @GetMapping("/user/by-email")
	public ResponseEntity<List<User_account>> getUderDetailsByEmail(@RequestParam("emailId") String emailId){
		List<User_account> user = userService.getUserDetailsByEmail(emailId);
		if(user != null) {
			return ResponseEntity.ok(user);
		}
		else {
			return ResponseEntity.notFound().build();
					}
		
	}
		
    
	//To get details of a user by email id
	@GetMapping("/user/{user_email}")
	User findByEmail(@PathVariable String user_email) throws ResourceNotFound
	{	User user= userService.getUserByEmail(user_email)
	.orElseThrow(() -> new ResourceNotFound("User not found for this id :: " + user_email,HttpStatus.NOT_FOUND));
       System.out.println(user_email);
    return user;
	}
	//Validation of format of email
	private boolean isValidEmail(String email)
	  {
		  return email.matches("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$");
	  }
	//For new registration
	  @PostMapping("/user/register")
	    public ResponseEntity<Object> creatingUser(@Validated @RequestBody User newUser)throws InvalidException {
		  String email=newUser.getUser_email();
		  String password=newUser.getUser_pwd();
		 
		  if(!isValidEmail(email))
		  {
			  throw new InvalidException("Invalid email",HttpStatus.BAD_REQUEST);
		  }
		 
		
		  if(!isValidPassword(password))
		  {
			  throw new InvalidException("Password should be of atleast 8 characters",HttpStatus.BAD_REQUEST);
		  }
		  
	        User user= userService.createUser(newUser);
	        System.out.println(user.getFirst_name());
	        return ResponseEntity.ok(user);
	    }
	  //For opening new account
	  @PostMapping("/user/open")
	  public ResponseEntity<Object> creatingUserAccount(@Validated @RequestBody User_account UserDetails)throws ResourceNotFound {
			 
	        String email=UserDetails.getemailId();
	        UserDetails.setAcc_bal(0l);
	        Date date = new Date();
	        SimpleDateFormat sdf  =  new SimpleDateFormat("yyyy-mm-dd");
	        String formattedDate = sdf.format(date);
	        
	        
	        UserDetails.setAcc_open_date(formattedDate);
	        
	        Optional<User>user=userService.getUserByEmail(email);
	        if(user.isPresent())
	        {
	        	User_account user_d=userService.createUserAccount(UserDetails);
	        	System.out.println(user_d.getAadhar_no());
	        	return ResponseEntity.ok(user_d);
	        }
	        else {
	        	throw new ResourceNotFound("This Email is not Registered-"+ email,HttpStatus.NOT_FOUND);
	        }
	  }
	
	
	 
	  
  private boolean isValidPassword(String password) {
	  return password.length()>=8;
	
  		}
	  @PostMapping("/validate")
	  public ResponseEntity<Object> validateLogin(@RequestBody User loginReq)throws ResourceNotFound, InvalidException, NotFoundException
	  {
		  String email=loginReq.getUser_email();
		  String pwd=loginReq.getUser_pwd();
		  
		  if(email==null|| email.isEmpty())
			  throw new NotFoundException("Please enter the email",HttpStatus.NOT_FOUND);
		  
		  if(!isValidEmail(email))
		  {
			  throw new InvalidException("Invalid email",HttpStatus.BAD_REQUEST);
		  }
		  if(pwd==null || pwd.isEmpty() ) 
	       {
			  throw new NotFoundException("Please enter the password",HttpStatus.NOT_FOUND);
	       }
			  
		  User user= userService.getUserByEmail(email)
					.orElseThrow(() -> new ResourceNotFound("User not found for this email :: " + email,HttpStatus.NOT_FOUND));
	       System.out.println(email);
	       
	   
	       BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
	       boolean passwordMatches=passwordEncoder.matches(pwd, user.getUser_pwd());
	       
	       if(pwd==null || !passwordMatches)
	    		   {
	       throw new InvalidException("Invalid password",HttpStatus.BAD_REQUEST);
	    		   }
	       return ResponseEntity.ok("{\"message\":\"Login Successful\"}");
	     
	       
	  }
	  
//	  @PutMapping("/withdraw1/{acc_no}/{amount}")
//	  public void Withdraw1(@PathVariable String acc_no, @PathVariable String amount) throws BalanceExceptions
//	  {
//		  float am  =  Float.parseFloat(amount); 
//		  float rem_balance = userService.Withdraw(acc_no, am);
//		  System.out.print(rem_balance);
//		  System.out.print(am);
//	  }
//	  
	  
	  
	  @PutMapping ("/withdraw")
	  public ResponseEntity<String> Withdraw(@RequestParam String acc_no, @RequestParam String amount) throws BalanceExceptions
	  {
		  
		  System.out.print(amount);
		  float am  =  Float.parseFloat(amount); 
		  float rem_balance = userService.Withdraw(acc_no, am);
		  if(rem_balance==-1)
		  {
			  throw new BalanceExceptions("Insufficient Balance in your account",HttpStatus.BAD_REQUEST);
		  }
		
		  String msg= String.format("Your remaining balance is , %s!", rem_balance);
		  return ResponseEntity.ok("{" + "\"message\": \"" + msg + "\"}");
	  }

	  
//	  "{\"message\":\"Your remaining balance is now \" + rem_balance\"}"
	  
	  
	  @PutMapping ("/deposit")
	  public ResponseEntity<String> Depoist(@RequestParam String acc_no, @RequestParam String amount) 
	  {
		  
		  System.out.print(amount);
		  float am  =  Float.parseFloat(amount); 
		  float rem_balance = userService.Deposit(acc_no, am);
		  
		
		  return ResponseEntity.ok("Your remaining balance is now " + rem_balance);
	  }
	
	  
	  
}
