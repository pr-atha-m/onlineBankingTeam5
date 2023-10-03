package com.onlinebanking.demo.controller;

import java.util.Optional;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinebanking.demo.entity.AuthRequest;
import com.onlinebanking.demo.entity.AuthResponse;
import com.onlinebanking.demo.entity.User;
import com.onlinebanking.demo.exceptions.InvalidException;
import com.onlinebanking.demo.repository.UserRepository;
import com.onlinebanking.demo.security.JwtHelper;
import com.onlinebanking.demo.service.CustomUserDetailsService;

@RestController
@RequestMapping("/authentication")
@CrossOrigin
public class AuthController {
	
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private JwtHelper helper;
	 org.slf4j.Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	 
	 
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) throws InvalidException
	{
		System.out.print("djdddfff PRINTTTTTTTTTT"+request.getPwd());
		String email = request.getEmail();
		String pwd =request.getPwd();
		
		Authentication auth = manager.authenticate(new UsernamePasswordAuthenticationToken(email, pwd));
		
//		this.doAuthenticate(request.getEmail(), request.getPwd());
		if(auth.isAuthenticated())
		{
			UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
	        System.out.print(userDetails.getUsername());
			 String token = this.helper.generateToken(userDetails.getUsername());
		        System.out.print("TOKENNN: " + token);
		        
		        AuthResponse res = AuthResponse.builder()
					             .token(token)
					             .email(userDetails.getUsername()).build();
		        System.out.print(token);
				
				
		        return new ResponseEntity<>(res, HttpStatus.OK);
		}
		
		else
		{
			throw new InvalidException("Invalid credentials", HttpStatus.BAD_REQUEST);
		}

        
       
        
	}
	
//	private void doAuthenticate(String email, String password) {
//
//        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
//        try {
//            manager.authenticate(authentication);
//
//
//        } catch (BadCredentialsException e) {
//            throw new BadCredentialsException(" Invalid Username or Password  !!");
//        }
//
//    }
//	
//	
//
//    @ExceptionHandler(BadCredentialsException.class)
//    public String exceptionHandler() {
//        return "{\"message\":\"Credentials Invalid !!\"}";
//    }
}