package com.onlinebanking.demo.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.onlinebanking.demo.entity.Transaction;
import com.onlinebanking.demo.exceptions.BalanceExceptions;
import com.onlinebanking.demo.exceptions.InvalidException;
import com.onlinebanking.demo.exceptions.NotFoundException;
import com.onlinebanking.demo.exceptions.ResourceNotFound;
import com.onlinebanking.demo.service.TransactionService;

@RestController
@RequestMapping("/transaction")
@CrossOrigin
public class TransactionController {
	
	@Autowired
	private TransactionService trans_service;
	
	@GetMapping("/transactionHistory/{acc_no}")
	public ResponseEntity<Optional<List<Transaction>>> transHistory (@PathVariable String acc_no) throws NotFoundException
	{
		Optional<List<Transaction>> temp = trans_service.transactionHistory(acc_no);
		if(temp.isPresent())
		{
			return ResponseEntity.ok(temp);
		}
		
		throw new NotFoundException("No transactions for this account",HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/save")
	public ResponseEntity<String> saveTransaction (@RequestBody Transaction trans)
	{
		DateTimeFormatter date_format= DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formatted_date = LocalDate.now().format(date_format);
		trans.setTrans_date(formatted_date);
		Transaction temp= trans_service.saveTransaction(trans);
		System.out.println(temp.getTrans_date());
		return ResponseEntity.ok("{\"message\":\"Transaction saved\"}");
	}
	
	
	@PostMapping("/execute")
	public ResponseEntity<String> ExecuteTransaction (@RequestBody Transaction trans) throws ResourceNotFound, BalanceExceptions, InvalidException
	{
			trans_service.executeTransaction(trans);
			return ResponseEntity.ok("{\"message\":\"Transaction is Successful\"}");
	}
	
//	@GetMapping("/transactionHistory")
//	public ResponseEntity<Map<Transaction,String>> transHistory (@PathVariable String acc_no) throws NotFoundException
//	{
//		Map<Transaction, String> temp = trans_service.transactionHistory(acc_no);
////		Gson gson=new Gson();
////		String json
//		if(temp.isEmpty())
//		{
//		throw new NotFoundException("No transactions for this account",HttpStatus.NOT_FOUND);
//		}
//		System.out.println(temp);
//		return ResponseEntity.ok(temp);
//		
//	}
	

}
