package com.onlinebanking.demo.controller;



import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.onlinebanking.demo.entity.Admin;
import com.onlinebanking.demo.entity.Transaction;
import com.onlinebanking.demo.entity.User;
import com.onlinebanking.demo.entity.User_account;
import com.onlinebanking.demo.exceptions.InvalidException;
import com.onlinebanking.demo.exceptions.NotFoundException;
import com.onlinebanking.demo.exceptions.ResourceNotFound;
import com.onlinebanking.demo.repository.UserAccountRepository;
import com.onlinebanking.demo.repository.UserRepository;
import com.onlinebanking.demo.service.AdminServiceInterface;
import com.onlinebanking.demo.service.TransactionService;
import com.onlinebanking.demo.service.UserServiceInterface;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins="http://localhost:8080")


public class AdminController {
	
	@Autowired
	AdminServiceInterface adminService;
	
	@Autowired
	UserServiceInterface userService;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	UserAccountRepository useraccRepo;

	@Autowired
	private TransactionService trans_service;
	
	public boolean isValidEmail(String email)
	  {
		  return email.matches("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$");
	  }
	private boolean isValidPassword(String password) {
		  return password.length()>=8;
		
	  		}
	
	@PostMapping("/register")
    public ResponseEntity<Object> creatingAdmin(@Validated @RequestBody Admin newadmin)throws InvalidException {
	  String email=newadmin.getAdmin_email();
	  String password=newadmin.getAdmin_pwd();
	  
	
		
	 
	  if(!isValidEmail(email))
	  {
		  throw new InvalidException("Invalid email",HttpStatus.BAD_REQUEST);
	  }
	 
	
	  if(!isValidPassword(password))
	  {
		  throw new InvalidException("Password should be of atleast 8 characters",HttpStatus.BAD_REQUEST);
	  }
	  
        Admin admin= adminService.createAdmin(newadmin);
        System.out.println(admin.getAdmin_user());
        return ResponseEntity.ok(admin);
    }
	
	@PostMapping("/validate")
	  public ResponseEntity<Object> validateLogin(@RequestBody Admin loginReq)throws ResourceNotFound, InvalidException, NotFoundException
	  {
		  String email=loginReq.getAdmin_email();
		  String pwd=loginReq.getAdmin_pwd();
		  
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
			  
		  Admin admin= adminService.getByEmail(email)
					.orElseThrow(() -> new ResourceNotFound("Admin not found for this email :: " + email,HttpStatus.NOT_FOUND));
	       System.out.println(email);
	       
	   
	       BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
	       boolean passwordMatches=passwordEncoder.matches(pwd, admin.getAdmin_pwd());
	       
	       if(pwd==null || !passwordMatches)
	    		   {
	       throw new InvalidException("Invalid password",HttpStatus.BAD_REQUEST);
	    		   }
	       return ResponseEntity.ok("{\"message\":\"Login Successful\"}");
	     
	       
	  }
	
	@GetMapping("/userdetails/{user_email}")
	public ResponseEntity<User> findByEmail(@PathVariable String user_email) throws ResourceNotFound
	{	User user= userService.getUserByEmail(user_email)
	.orElseThrow(() -> new ResourceNotFound("User not found for this email :: " + user_email,HttpStatus.NOT_FOUND));
       System.out.println(user_email);
    return ResponseEntity.ok(user);
	}

	
	//To get details of a useraccounts details by email id
	 @GetMapping("/useraccounts")
		public ResponseEntity<List<User_account>> getUserDetailsByEmail(@RequestParam("emailId") String emailId) throws ResourceNotFound{
			List<User_account> user = userService.getUserDetailsByEmail(emailId);
			if(user.isEmpty()) {
				throw new ResourceNotFound("No Accounts associated with this email::"+emailId,HttpStatus.NOT_FOUND);
				
			}
			else {
				return ResponseEntity.ok(user);
						}
			
		}
	 
	 
		
		
		@GetMapping("/transactionHistory/{acc_no}")
		public ResponseEntity<Optional<List<Transaction>>> transHistory (@PathVariable String acc_no) throws NotFoundException
		{
			Optional<List<Transaction>> temp = trans_service.transactionHistory(acc_no);
			if(temp.isPresent())
			{
				return ResponseEntity.ok(temp);
			}
			
			throw new NotFoundException("No transactions for this account",HttpStatus.NOT_FOUND);
		}
			
		@PutMapping("/setStatus")
		public ResponseEntity<String> setUserstatus(@RequestParam("acc_num")String acc_no)
		{
			Optional<User_account> user=useraccRepo.findById(acc_no);
			user.get().setStatus(!(user.get().isStatus()));
			useraccRepo.save(user.get());
			String X;
			if(user.get().isStatus())
				X="enabled";
			else
				X="disabled";
			return ResponseEntity.status(HttpStatus.OK).contentType(org.springframework.http.MediaType.APPLICATION_JSON).body("User status is "+ X);
	        
		}
		
		
	

}
