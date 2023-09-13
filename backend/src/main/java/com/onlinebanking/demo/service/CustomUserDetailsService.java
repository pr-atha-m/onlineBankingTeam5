package com.onlinebanking.demo.service;

import com.onlinebanking.demo.entity.User;
import com.onlinebanking.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	//If it is existing in database
        Optional<User> user = repository.findByEmail(username);
        //pass it to User object of security
        if(user!=null)
        {
        	return new org.springframework.security.core.userdetails.User(user.get().getUser_email(), user.get().getUser_pwd(), new ArrayList<>());
        }
        else
        {
        	throw new UsernameNotFoundException("User not found");
        }
        
    }
}
