package com.onlinebanking.demo.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;


import com.onlinebanking.demo.entity.User;
import com.onlinebanking.demo.entity.User_account;
import com.onlinebanking.demo.exceptions.BalanceExceptions;
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
	public List<User_account> getUser()
	{
		return userAccountRepo.findAll();
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
		DateTimeFormatter date_format= DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formatted_date = LocalDate.now().format(date_format);
		user.setAcc_open_date(formatted_date);
		user.setAcc_no(generateAccountNumber());
		return userAccountRepo.save(user) ; 
	}
	@Override
	public List<User_account> getUserDetailsByEmail(String emailId) {

		return userAccountRepo.findByEmailId(emailId);
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
	public float Withdraw(String acc_no, float amount) throws BalanceExceptions {
		// TODO Auto-generated method stub
		Optional<User_account> acc= userAccountRepo.findById(acc_no);
		
		System.out.print("Hello");
		if (amount<0)
		{
			throw new BalanceExceptions("amount cannot be negative",HttpStatus.BAD_REQUEST);
		}
		if(acc.get().getBalance() >=amount)
		{
			float new_bal = acc.get().getBalance()-amount;
			acc.get().setBalance(new_bal);
			userAccountRepo.save(acc.get());
			return new_bal;
		}
		return -1;
	}
	
	@Override
	public float Deposit(String acc_no, float amount) {
		// TODO Auto-generated method stub
		Optional<User_account> acc= userAccountRepo.findById(acc_no);
		
			float new_bal = acc.get().getBalance()+amount;
			acc.get().setBalance(new_bal);
			userAccountRepo.save(acc.get());
			return new_bal;
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
//	public User_account updateUser(String user_email, User_account userDetails) {
//		Optional<User_account> updateduser=userAccountRepo.findById(user_email);   
//		updateduser.get().setGross_annual_income(userDetails.getGross_annual_income());
//		updateduser.get().setOcc_type(userDetails.getOcc_type());
//		updateduser.get().setPerm_addr(userDetails.getPerm_addr());
//		updateduser.get().setRes_addr(userDetails.getRes_addr());
//		updateduser.get().setPhone_no(userDetails.getPhone_no());
//		updateduser.get().setSource_of_income(userDetails.getSource_of_income());
//		userAccountRepo.save(updateduser);
//		
	}

//	@Override
//	public Map<String, Boolean> deleteUser(String user_email) {
//		// TODO Auto-generated method stub
//		return null;
//	}


