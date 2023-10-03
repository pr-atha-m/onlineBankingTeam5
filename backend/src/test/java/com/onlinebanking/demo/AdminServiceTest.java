package com.onlinebanking.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.onlinebanking.demo.entity.Admin;
import com.onlinebanking.demo.entity.User;
import com.onlinebanking.demo.repository.AdminRepository;
import com.onlinebanking.demo.repository.UserAccountRepository;
import com.onlinebanking.demo.repository.UserRepository;
import com.onlinebanking.demo.service.AdminService;
import com.onlinebanking.demo.service.UserService;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AdminServiceTest {
	
	@InjectMocks
	private AdminService adminService;
	
	@Mock
	private AdminRepository adminRepo;
	
	@Mock
	private UserRepository userRepo;
	
	@Mock
	private UserService userService;
	
	@Test
	public void sample()
	{
		assertEquals("hello", "hello");
	}
	
	@Test
	public void testByEmail()
	{
		String email ="admin@gmail.com";
		Admin admin = new Admin("admin","admin@gmail.com","12345678");
		
		when(adminRepo.findById(email)).thenReturn(Optional.of(admin));
		
		Optional<Admin> admin1 = adminService.getByEmail(email);
		Admin temp=admin1.get();
		
		assertNotNull(temp);
		
		assertEquals(admin.getAdmin_user(),temp.getAdmin_user());
		assertEquals(admin.getAdmin_pwd(),temp.getAdmin_pwd());
		assertEquals(admin.getAdmin_email(),temp.getAdmin_email());
		
		verify(adminRepo, times(1)).findById(email);
	}
	
	@Test
	public void testCreateAccount ()
	{
		
		Admin admin = new Admin("admin","admin@gmail.com","12345678");
		
		when(adminRepo.save(any(Admin.class))).thenReturn(admin);
		
		Admin temp = adminService.createAdmin(admin);
		
		assertNotNull(temp);
		assertEquals(admin.getAdmin_user(),temp.getAdmin_user());
		assertEquals(admin.getAdmin_email(),temp.getAdmin_email());
		assertEquals(admin.getAdmin_pwd(),temp.getAdmin_pwd());
		
		verify(adminRepo,times(1)).save(admin);
		
		
	}
	

}
