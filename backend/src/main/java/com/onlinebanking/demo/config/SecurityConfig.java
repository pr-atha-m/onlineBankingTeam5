package com.onlinebanking.demo.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.onlinebanking.demo.repository.UserRepository;
import com.onlinebanking.demo.security.JwtAuthenticationEntryPoint;
import com.onlinebanking.demo.security.JwtFilter;


@Configuration
public class SecurityConfig {
	
	@Autowired
    private JwtAuthenticationEntryPoint point;
    @Autowired
    private JwtFilter filter;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
	
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
    	http.csrf(csrf->csrf.disable())
    		.cors(cors->cors.disable())
    		.authorizeHttpRequests(auth->auth
    				.requestMatchers(new AntPathRequestMatcher("/**")).permitAll()
    				.anyRequest().authenticated())
    				.exceptionHandling(ex->ex.authenticationEntryPoint(point))
    				.sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    	;
    	
    	http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    	return http.build();
    }
	
	
	
	 
	 @Bean 
	 public DaoAuthenticationProvider doDaoAuthenticationProvider()
	 {
		 DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		 dao.setUserDetailsService(userDetailsService);
		 dao.setPasswordEncoder(passwordEncoder);
		 return dao;
	 }
	
	}
	
	
	
   

