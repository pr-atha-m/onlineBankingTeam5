package com.onlinebanking.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.onlinebanking.demo.entity.User;
import com.onlinebanking.demo.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	
	@Autowired
	UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> user = userRepo.findById(username);
		if(user.isPresent())
		{
			return user.get();
		}
		
		throw new UsernameNotFoundException("User not found with email " + username);
	}
	
}
