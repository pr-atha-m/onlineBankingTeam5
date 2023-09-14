package com.onlinebanking.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;


import com.onlinebanking.demo.entity.User;
import com.onlinebanking.demo.entity.User_account;
import com.onlinebanking.demo.repository.UserAccountRepository;
import com.onlinebanking.demo.repository.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Service
public class UserService implements UserServiceInterface {
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	UserAccountRepository userAccountRepo;
	@Autowired
	private EntityManager entityManager;
	
	private final BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
	
	

	
	
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
		 String hashPassword= passwordEncoder.encode(user.getUser_pwd());
		user.setUser_pwd(hashPassword
				);
		return userRepo.save(user);
	}
	
	@Override
	public User_account createUserAccount(@Validated @RequestBody User_account user)
	{
		user.setAcc_no(generateAccountNumber());
		return userAccountRepo.save(user) ; 
	}


//	@Override
//	public Accounts createAccount(@Validated @RequestBody Accounts user){
//		return accountRepo.save(user);
//	}

	private String generateAccountNumber() {
        String query = "SELECT MAX(acc_no) FROM user_account";
        Query maxQuery = entityManager.createNativeQuery(query);
        String maxAccountNumber = (String) maxQuery.getSingleResult();
        
        int nextNumber = 1;
       
        if (maxAccountNumber != null && maxAccountNumber.length()>=2 ) {
            try {
                nextNumber = Integer.parseInt(maxAccountNumber.substring(2)) + 1;
            } catch (NumberFormatException e) {
                // Handle parsing error as needed
            }
        }
       
        return String.format("1%011d", nextNumber);
	}
	
	
	
	@Override
	public User_account getBalanceByAccountNumber(String acc_no)
	{
		return userRepo.findById(user_email);
	}
	
	
	
	
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


