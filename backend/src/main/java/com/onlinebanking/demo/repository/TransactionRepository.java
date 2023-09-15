package com.onlinebanking.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinebanking.demo.entity.Transaction;


public interface TransactionRepository extends JpaRepository <Transaction,String>{

}
