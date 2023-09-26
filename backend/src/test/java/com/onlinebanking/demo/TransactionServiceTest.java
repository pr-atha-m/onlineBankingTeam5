package com.onlinebanking.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.onlinebanking.demo.entity.Transaction;
import com.onlinebanking.demo.entity.User;
import com.onlinebanking.demo.entity.User_account;
import com.onlinebanking.demo.exceptions.BalanceExceptions;
import com.onlinebanking.demo.exceptions.InvalidException;
import com.onlinebanking.demo.exceptions.ResourceNotFound;
import com.onlinebanking.demo.entity.User_account;
import com.onlinebanking.demo.repository.TransactionRepository;
import com.onlinebanking.demo.repository.UserAccountRepository;
import com.onlinebanking.demo.repository.UserRepository;
import com.onlinebanking.demo.service.TransactionService;
import com.onlinebanking.demo.service.UserService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Arrays;
import java.util.Date;


	
	

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {
	
	@InjectMocks 
	private TransactionService transService;
	
	@InjectMocks 
	private UserService accService;
	
	@Mock
	private TransactionRepository transRepo;
	
	@Mock
	private UserAccountRepository userAccRepo;
	
	
	@Test
	public void sample()
	{
		assertEquals("hello", "hello");
	}
	
	@Test
    public void testSaveTransaction() {
		
		Transaction trans = new Transaction (1,"100000000008","100000000009","20","2023-04-05","nothing","Online","no");
		when(transRepo.save(any(Transaction.class))).thenReturn(trans);
		
		Transaction trans_stored = transService.saveTransaction(trans);
		assertNotNull(trans_stored);
		
		assertEquals(trans, trans_stored);
		
		verify(transRepo,times(1)).save(trans);
		
	}
	
	@Test
	public void testExecuteTransaction() throws ResourceNotFound, InvalidException, BalanceExceptions
	{
		DateTimeFormatter date_format= DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formatted_date = LocalDate.now().format(date_format);
		float in = 9876.0f;
		
		User_account sender = new User_account (1,formatted_date,"b@gmail.com","100000000006","Savings","9082582204",
		"salman", "939619640567",new Date(),"mumbai", "mumbai", "business", 9000.0f, "stocks",false,false,9876.0f,true);
		
		User_account receiver = new User_account (2,formatted_date,"c@gmail.com","100000000007","NRI","9082882204",
				"salman", "939619641567",new Date(),"mumbai", "mumbai", "business", 9000.0f, "stocks",false,false,9876.0f,true);
		
		 when(userAccRepo.findById(sender.getAcc_no())).thenReturn(Optional.of(sender));
	     when(userAccRepo.findById(receiver.getAcc_no())).thenReturn(Optional.of(receiver));
	     
	     Transaction trans = new Transaction (1,"100000000006","100000000007","20","2023-04-05","nothing","Online","no");
		 when(transRepo.save(any(Transaction.class))).thenReturn(trans);
			
	     transService.executeTransaction(trans);
	     
	     float amount = Float.parseFloat(trans.getAmount());
	     float delta = 0.0001f;
	     assertEquals((in-amount),sender.getBalance(),delta);
	     
	     assertEquals(in+amount,receiver.getBalance(),delta);
	     
	     verify(userAccRepo,times(1)).findById(sender.getAcc_no());
	     verify(transRepo, times(1)).save(trans);     
	        
	}
	

}
