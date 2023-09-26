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

import com.onlinebanking.demo.entity.User;
import com.onlinebanking.demo.entity.User_account;
import com.onlinebanking.demo.exceptions.BalanceExceptions;
import com.onlinebanking.demo.entity.User_account;
import com.onlinebanking.demo.repository.UserAccountRepository;
import com.onlinebanking.demo.repository.UserRepository;
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
public class UserServiceTest {
	
	@InjectMocks 
	private UserService userService;
	
	@Mock
	private UserRepository userRepo;
	
	@Mock
	private UserAccountRepository userAccRepo;
	
	@Test
	public void sample()
	{
		assertEquals("hello", "hello");
	}
	
	@Test
	public void testgetUser()
	{
		List<User> users = new ArrayList<> ();
		User user1 = new User("joy", "dbfjgbeb","e@gmail.com","1224556678");
		User user2 = new User("j", "b@gmail.com","123445678","pugrohit");
		
		users= Arrays.asList(user1, user2);
		
		when(userRepo.findAll()).thenReturn(users);
		
		List<User> current = userService.getUser();
		assertNotNull(current);
		assertEquals(users.size(),current.size());
		
		for(int i=0;i<current.size();i++)
		{
			User temp1 = users.get(i);
			User temp2 = current.get(i);
			assertEquals(temp1.getFirst_name(),temp2.getFirst_name());
			assertEquals(temp1.getLast_name(),temp2.getLast_name());
			assertEquals(temp1.getUser_email(),temp2.getUser_email());
			assertEquals(temp1.getUser_pwd(),temp2.getUser_pwd());
			System.out.print("test case passed");
		}
		
		verify(userRepo, times(1)).findAll();
	
	}
	
	@Test
	public void testByEmail()
	{
		String email ="a@gmail.com";
		User user1 = new User("joy", "dbfjgbeb","e@gmail.com","1224556678");
		
		when(userRepo.findById(email)).thenReturn(Optional.of(user1));
		
		Optional<User> user = userService.getUserByEmail(email);
		User temp = user.get();
		
		assertNotNull(temp);
		assertEquals(user1.getFirst_name(),temp.getFirst_name());
		assertEquals(user1.getLast_name(),temp.getLast_name());
		assertEquals(user1.getUser_email(),temp.getUser_email());
		assertEquals(user1.getUser_pwd(),temp.getUser_pwd());
		
		verify(userRepo, times(1)).findById(email);
	}
	
//	@Test
//	public void testCreateAccount ()
//	{
//		
//		DateTimeFormatter date_format= DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		String formatted_date = LocalDate.now().format(date_format);
//		User_account userAcc = new User_account (1,formatted_date,"b@gmail.com","100000000006","Savings","9082582204",
//				"salman", "939619640567",new Date(),"mumbai", "mumbai", "business", 9000.0f, "stocks",false,false,9876.0f,true);
//		
//		when(userAccRepo.save(any(User_account.class))).thenReturn(userAcc);
//		
//		User_account temp = userService.createUserAccount(userAcc);
//		float delta = 0.001f;
//		assertNotNull(temp);
//		assertEquals(userAcc.getAadhar_no(),temp.getAadhar_no());
//		assertEquals(userAcc.getBalance(),temp.getBalance(),delta);
//		
//		verify(userAccRepo,times(1)).save(userAcc);
//		
//		
//	}
//	
	@Test
	public void testWithdraw() throws BalanceExceptions
	{
		String acc_no = "100000000006";
		float amount = 100.0f;
		float in = 9876.0f; 
		
		DateTimeFormatter date_format= DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formatted_date = LocalDate.now().format(date_format);
		
		User_account userAcc = new User_account (1,formatted_date,"b@gmail.com","100000000006","Savings","9082582204",
				"salman", "939619640567",new Date(),"mumbai", "mumbai", "business", 9000.0f, "stocks",false,false,9876.0f,true);
		
		when(userAccRepo.findById(acc_no)).thenReturn(Optional.of(userAcc));
		
			
				float new_bal = userService.Withdraw(acc_no, amount);
				float delta  = 0.0001f;
				assertEquals(in-amount, new_bal, delta);
				
		verify(userAccRepo,times(1)).findById(acc_no);
				
	}
	
	@Test
	public void testDeposit()
	{
		String acc_no = "100000000005";
		float amount = 100.0f;
		float in = 9876.0f; 
		
		DateTimeFormatter date_format= DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formatted_date = LocalDate.now().format(date_format);
		
		User_account userAcc = new User_account (1,formatted_date,"b@gmail.com","100000000005","Savings","9082582204",
				"salman", "939619640567",new Date(),"mumbai", "mumbai", "business", 9000.0f, "stocks",false,false,9876.0f,true);
		
		when(userAccRepo.findById(acc_no)).thenReturn(Optional.of(userAcc));
		
			
				float new_bal = userService.Deposit(acc_no, amount);
				float delta  = 0.0001f;
				assertEquals(in+amount, new_bal, delta);
				
		verify(userAccRepo,times(1)).findById(acc_no);
				
	}
	

}
