package com.onlinebanking.demo.service;

import java.util.Optional;

import com.onlinebanking.demo.entity.Transaction;
import com.onlinebanking.demo.entity.User_account;

public interface TransactionServiceInterface {
	
	Transaction saveTransaction (Transaction trans);
	void executeTransaction (Transaction trans);
	void performTransaction(Optional<User_account> sender_account, Optional<User_account> receiver_account, Transaction trans);

}
