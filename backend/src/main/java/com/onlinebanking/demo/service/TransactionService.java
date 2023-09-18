package com.onlinebanking.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Transactional;

import com.onlinebanking.demo.entity.Transaction;
import com.onlinebanking.demo.entity.User_account;
import com.onlinebanking.demo.repository.TransactionRepository;
import com.onlinebanking.demo.repository.UserAccountRepository;

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
	public void executeTransaction(Transaction trans) {
		// TODO Auto-gengetByIdethod stub
		Optional<User_account> sender = accRepo.findById(trans.getSender_account());
		if(sender!=null)
		{
			Optional<User_account> receiver = accRepo.findById(trans.getReceiver_account());
			
			if(receiver !=null)
			{
				float amount = trans.getAmount();
				if (sender.get().getBalance() >= amount)
				{
					performTransaction(sender, receiver, trans);
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
