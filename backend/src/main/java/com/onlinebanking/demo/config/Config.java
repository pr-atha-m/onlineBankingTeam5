package com.onlinebanking.demo.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.*;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.onlinebanking.demo.entity.User;
import com.onlinebanking.demo.repository.UserRepository;

@Configuration
public class Config {
    
	@Autowired
	private UserRepository userRepo;
	
	@Bean
	public UserDetailsService userDetailsService()
	{
		return new UserDetailsService()
				{

					@Override
					public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
						// TODO Auto-generated method stub
						Optional<User> user = userRepo.findById(username);
						User temp = user.get();
						
						if(user.isPresent())
						{
							
							return org.springframework.security.core.userdetails.User.builder().
									username(temp.getUser_email()).password(temp.getUser_pwd()).build();
						}
						else
						{
							throw new UsernameNotFoundException("User not there with this email: " + temp.getUser_email());
						}
					}
					
				};
		
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
    
    @Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception
	{
		return builder.getAuthenticationManager();
	}
    
    
}

