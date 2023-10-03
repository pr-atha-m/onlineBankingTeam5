package com.onlinebanking.demo.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.onlinebanking.demo.entity.Transaction;
import com.onlinebanking.demo.entity.User_account;
import com.onlinebanking.demo.exceptions.BalanceExceptions;
import com.onlinebanking.demo.exceptions.InvalidException;
import com.onlinebanking.demo.exceptions.ResourceNotFound;

public interface TransactionServiceInterface {
	
	Transaction saveTransaction (Transaction trans);
	void executeTransaction (Transaction trans) throws ResourceNotFound, BalanceExceptions, InvalidException;
	void performTransaction(Optional<User_account> sender_account, Optional<User_account> receiver_account, Transaction trans);
	Optional<List<Transaction>> transactionHistory(String acc_no);
}
