package com.onlinebanking.demo.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Transactional;

import com.onlinebanking.demo.entity.Transaction;
import com.onlinebanking.demo.entity.User;
import com.onlinebanking.demo.entity.User_account;
import com.onlinebanking.demo.exceptions.BalanceExceptions;
import com.onlinebanking.demo.exceptions.InvalidException;
import com.onlinebanking.demo.exceptions.ResourceNotFound;
import com.onlinebanking.demo.repository.TransactionRepository;
import com.onlinebanking.demo.repository.UserAccountRepository;
import com.onlinebanking.demo.repository.UserRepository;


@Service
public class TransactionService implements TransactionServiceInterface{
	
	@Autowired
	TransactionRepository transRepo;
	
	@Autowired
	UserAccountRepository accRepo;
	
	@Autowired
	UserRepository userRepo;

	@Override
	public Transaction saveTransaction(Transaction trans) {
		return transRepo.save(trans);
	}

	@Override
	@Transactional(rollbackFor = TransactionException.class)
	public void executeTransaction(Transaction trans) throws ResourceNotFound,InvalidException, BalanceExceptions {
		// TODO Auto-gengetByIdethod stub
		Optional<User_account> sender = accRepo.findById(trans.getSender_account());
		
		
		
		if(sender.get().isStatus()==false)
		{
			throw new InvalidException("Your User account has been disabled by the admin",HttpStatus.BAD_REQUEST);
		}
		
		DateTimeFormatter date_format= DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formatted_date = LocalDate.now().format(date_format);
		trans.setTrans_date(formatted_date);
		
		
		if(sender!=null)
		{
			Optional<User_account> receiver = accRepo.findById(trans.getReceiver_account());
			
			if(receiver ==null)
			{
				throw new ResourceNotFound("Enter the receiver's account number",HttpStatus.NOT_FOUND);
			}
			else if(receiver.isEmpty())
			{
				throw new ResourceNotFound("No account found with this account number",HttpStatus.NOT_FOUND);
			}
			else if(sender==receiver)
			{
				throw new InvalidException("Transfer cannot done to same account",HttpStatus.BAD_REQUEST);
			}
			else {
				
				saveTransaction(trans);
			String am = trans.getAmount();
			
				float amount = Float.parseFloat(am);
				if(amount<0)
				{
					throw new BalanceExceptions("amount cannot be negative",HttpStatus.BAD_REQUEST);
				}
				if (sender.get().getBalance() >= amount)
				{
					performTransaction(sender, receiver, trans);
				}
				else
				{
					throw new BalanceExceptions("Insufficient Balance in your account",HttpStatus.BAD_REQUEST);
				}
			}
		}
	}

	@Override
	public void performTransaction(Optional<User_account> sender_account, Optional<User_account> receiver_account,
			Transaction trans) {
		// TODO Auto-generated method stub
		String am = trans.getAmount();
		float amount = Float.parseFloat(am);
		sender_account.get().setBalance(sender_account.get().getBalance()-amount);
		receiver_account.get().setBalance(receiver_account.get().getBalance()+amount);
		
		transRepo.save(trans);
		accRepo.save(sender_account.get());
		accRepo.save(receiver_account.get());
		
	}

	@Override
	public Optional<List<Transaction>> transactionHistory(String acc_no) {
		// TODO Auto-generated method stub
		Optional<List<Transaction>> temp = transRepo.findTransactionHistory(acc_no);
		if (temp.isPresent())
		{
			return temp;
		}
		return Optional.empty();
	}
	
	

	
}
