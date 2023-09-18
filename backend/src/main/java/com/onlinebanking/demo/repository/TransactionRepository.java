package com.onlinebanking.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.onlinebanking.demo.entity.Transaction;


public interface TransactionRepository extends JpaRepository <Transaction,String>{
	
	@Query(value="select * from Transaction_table trans where trans.sender_account=?1 or trans.receiver_account=?1", nativeQuery=true)
	Optional<List<Transaction>> findTransactionHistory (String acc_no);

}
