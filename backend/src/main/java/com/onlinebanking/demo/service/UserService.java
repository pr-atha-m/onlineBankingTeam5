package com.onlinebanking.demo.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import com.onlinebanking.demo.entity.User;
import com.onlinebanking.demo.repository.UserRepository;

@Service
public class UserService implements UserServiceInterface {
	@Autowired
	UserRepository userRepo;
	
	@Override
	public List<User> getUser()
	{
		return userRepo.findAll();
	}
	
	@Override
	public Optional<User>getUserByEmail(String user_email)
	{
		return userRepo.findById(user_email);
	}
	
	@Override
	public User createUser(@Validated @RequestBody User user)
	{
		return userRepo.save(user);
	}
//	
//	@Override
//	public ResponseEntity<User> updateUser(String user_email ,@Validated@RequestBody User changedUser)
//	{
//		//User updatedUser=userRepo.findById(user_email);
//		/updatedUser.setUser_name(changedUser.getUser_name());
//		
//				
//	}

//	@Override
//	public ResponseEntity<User> updateUser(String user_email, User userDetails) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Map<String, Boolean> deleteUser(String user_email) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
