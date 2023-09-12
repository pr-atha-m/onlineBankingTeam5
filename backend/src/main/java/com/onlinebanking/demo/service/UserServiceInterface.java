package com.onlinebanking.demo.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.onlinebanking.demo.entity.User;
import com.onlinebanking.demo.entity.User_account;
//import com.onlinebanking.demo.exception.ResourceNotFoundException;

public interface UserServiceInterface {
	List<User> getUser();
	Optional<User> getUserByEmail(String user_email);
  User createUser(User user);
  User_account createUserAccount(User_account user);
//   ResponseEntity<User> updateUser(String user_email, User userDetails) ;
//   Map<String, Boolean> deleteUser(String user_email) ;
   

}
