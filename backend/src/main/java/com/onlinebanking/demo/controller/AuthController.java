package com.onlinebanking.demo.controller;




import com.onlinebanking.demo.entity.*;
import com.onlinebanking.demo.service.CustomUserDetailsService;
import com.onlinebanking.demo.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
//    @Autowired
//    private CustomUserDetailsService customUserDetailsService;

    @GetMapping("/home")
    public String welcome() {
        return "Welcome page";
    }
    
    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUser_email(), authRequest.getUser_pwd())
            );
        } catch (Exception ex) {
            throw new Exception("Bad credentials");
        }
        return jwtUtil.generateToken(authRequest.getUser_email());
    }

    
    @GetMapping(value = "/validate")
	public boolean getValidation(@RequestHeader("Authorization") String token){
		token = token.substring(7);
		AuthResponse auth = new AuthResponse();
	
		//log.info("Token validation for "+jwtUtil.extractUsername(token));
		
		if(jwtUtil.validateToken(token)) {
			
			System.out.println("Token validated");
			return true;
		}
		else {
			System.out.println("Token NOT validated");
			return false;
	}
    }}





