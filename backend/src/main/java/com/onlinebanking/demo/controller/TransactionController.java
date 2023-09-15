package com.onlinebanking.demo.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinebanking.demo.entity.Transaction;
import com.onlinebanking.demo.service.TransactionService;

@RestController
@RequestMapping("/transaction")
@CrossOrigin(origins="http://localhost:8080")
public class TransactionController {
	
	@Autowired
	private TransactionService trans_service;
	
	@PostMapping("/save")
	public ResponseEntity<String> saveTransaction (@RequestBody Transaction trans)
	{
		DateTimeFormatter date_format= DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formatted_date = LocalDate.now().format(date_format);
		trans.setTrans_date(formatted_date);
		Transaction temp= trans_service.saveTransaction(trans);
		System.out.println(temp.getTrans_date());
		return ResponseEntity.ok("Transaction saved");
	}
	
	
	@PostMapping("/execute")
	public ResponseEntity<String> ExecuteTransaction (@RequestBody Transaction trans)
	{
			trans_service.executeTransaction(trans);
			return ResponseEntity.ok("Transaction is successfull");
	}
	

}
