package com.onlinebanking.demo.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.onlinebanking.demo.entity.User;
import com.onlinebanking.demo.entity.User_account;
import com.onlinebanking.demo.exceptions.BalanceExceptions;
import com.onlinebanking.demo.exceptions.InvalidException;

//import com.onlinebanking.demo.exception.ResourceNotFoundException;

public interface UserServiceInterface {
	List<User_account> getUser();
	Optional<User> getUserByEmail(String user_email);

  User createUser(User user);
  
  User_account createUserAccount(User_account user);
  List<User_account> getUserDetailsByEmail(String emailId);
  float Withdraw (String acc_no, float amount) throws BalanceExceptions, InvalidException;
  float Deposit (String acc_no, float amount) throws InvalidException;
//User_account updateUser(String user_email, User_account userDetails);
  
//  Accounts createAccount(Accounts user);
  
  
//   ResponseEntity<User> updateUser(String user_email, User userDetails) ;
//   Map<String, Boolean> deleteUser(String user_email) ;
   

}
