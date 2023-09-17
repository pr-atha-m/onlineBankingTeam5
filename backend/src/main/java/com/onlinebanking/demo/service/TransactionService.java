package com.onlinebanking.demo.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Transactional;

import com.onlinebanking.demo.entity.Transaction;
import com.onlinebanking.demo.entity.User_account;
import com.onlinebanking.demo.exceptions.BalanceExceptions;
import com.onlinebanking.demo.exceptions.ResourceNotFound;
import com.onlinebanking.demo.repository.TransactionRepository;
import com.onlinebanking.demo.repository.UserAccountRepository;


@Service
public class TransactionService implements TransactionServiceInterface{
	
	@Autowired
	TransactionRepository transRepo;
	
	@Autowired
	UserAccountRepository accRepo;

	@Override
	public Transaction saveTransaction(Transaction trans) {
		return transRepo.save(trans);
	}

	@Override
	@Transactional(rollbackFor = TransactionException.class)
	public void executeTransaction(Transaction trans) throws ResourceNotFound, BalanceExceptions {
		// TODO Auto-gengetByIdethod stub
		Optional<User_account> sender = accRepo.findById(trans.getSender_account());
		if(sender!=null)
		{
			Optional<User_account> receiver = accRepo.findById(trans.getReceiver_account());
			
			if(receiver ==null)
			{
				throw new ResourceNotFound("Account not found with this account number",HttpStatus.NOT_FOUND);
			}
			else {
				float amount = trans.getAmount();
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
		float amount = trans.getAmount();
		sender_account.get().setBalance(sender_account.get().getBalance()-amount);
		receiver_account.get().setBalance(receiver_account.get().getBalance()+amount);
		
		transRepo.save(trans);
		accRepo.save(sender_account.get());
		accRepo.save(receiver_account.get());
		
	}

	
	

	
}
