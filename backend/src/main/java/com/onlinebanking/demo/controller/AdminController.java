package com.onlinebanking.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinebanking.demo.entity.Admin;
import com.onlinebanking.demo.exceptions.InvalidException;
import com.onlinebanking.demo.service.AdminServiceInterface;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins="http://localhost:8080")


public class AdminController {
	
	@Autowired
	AdminServiceInterface adminService;
	
	public boolean isValidEmail(String email)
	  {
		  return email.matches("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$");
	  }
	private boolean isValidPassword(String password) {
		  return password.length()>=8;
		
	  		}
	
	@PostMapping("register")
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

}
