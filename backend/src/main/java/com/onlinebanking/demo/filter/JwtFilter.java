package com.onlinebanking.demo.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.onlinebanking.demo.service.CustomUserDetailsService;
import com.onlinebanking.demo.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter 
{
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private CustomUserDetailsService customUserDetailService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String token_header= request.getHeader("Authorization");
		String email=null;
		String token=null;
		
		//Checking null and format
		if(token_header!=null && token_header.startsWith("Bearer "))
		{
			token=token_header.substring(7);
			
		
		
		try
		{
			email= this.jwtUtil.extractUsername(token);
			
		}
		catch (Exception e )
		{
			e.printStackTrace();
		}
		
		//Security now
		UserDetails userDetails= this.customUserDetailService.loadUserByUsername(email);
		
		if(email!=null && SecurityContextHolder.getContext().getAuthentication()==null)
		{
		
			UsernamePasswordAuthenticationToken upa_token =new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			upa_token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			
			SecurityContextHolder.getContext().setAuthentication(upa_token);
			
			if (jwtUtil.validateToken(token, userDetails)) {
				
				                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
				                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				                usernamePasswordAuthenticationToken
				                        .setDetails(new WebAuthenticationDetailsSource().buildDetails((jakarta.servlet.http.HttpServletRequest) request));
				                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				            }
		}
		
	
	}
	
	filterChain.doFilter(request, response);
	
}

}
//import com.onlinebanking.demo.service.CustomUserDetailsService;
//import com.onlinebanking.demo.util.*;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
////Check Token Validaity
//public abstract class JwtFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private JwtUtil jwtUtil;
//    @Autowired
//    private CustomUserDetailsService service;
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//
//        String authorizationHeader = httpServletRequest.getHeader("Authorization");
//
//        String token = null;
//        String userName = null;
//
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            token = authorizationHeader.substring(7);
//            userName = jwtUtil.extractUsername(token);
//        }
//
//        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//
//            UserDetails userDetails = service.loadUserByUsername(userName);
//
//            if (jwtUtil.validateToken(token, userDetails)) {
//
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
//                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                usernamePasswordAuthenticationToken
//                        .setDetails(new WebAuthenticationDetailsSource().buildDetails((jakarta.servlet.http.HttpServletRequest) httpServletRequest));
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//            }
//        }
//        filterChain.doFilter(httpServletRequest, httpServletResponse);
//    }
//}