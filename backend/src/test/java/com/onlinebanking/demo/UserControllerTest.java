package com.onlinebanking.demo;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.onlinebanking.demo.controller.UserController;
import com.onlinebanking.demo.entity.User_account;
import com.onlinebanking.demo.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {
	
	ObjectMapper objectMapper = new ObjectMapper();
	ObjectWriter objectwriter = objectMapper.writer();
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	private UserController userController;
	
	@Mock
	private UserService userService;
	
	private List<User_account> users;
	
	@BeforeEach
	public void init()
	{
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
		
		DateTimeFormatter date_format= DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formatted_date = LocalDate.now().format(date_format);
		
		User_account user1 = new User_account (1,formatted_date,"b@gmail.com","100000000006","Savings","9082582204",
				"salman", "939619640567",new Date(),"mumbai", "mumbai", "business", 9000.0f, "stocks",false,false,9876.0f,true);
		User_account user2 = new User_account (1,formatted_date,"e@gmail.com","100000000007","Savings","9082582204",
				"salman", "939619650567",new Date(),"mumbai", "mumbai", "business", 9000.0f, "stocks",false,false,9876.0f,true);
		
		
		users = Arrays.asList(user1, user2);
	}
	
	@Test
	public void sample()
	{
		assertEquals("hi", "hi");
	}
	
	@Test
	public void testGetUserDetails () throws Exception
	{
		when(userService.getUser()).thenReturn(users);
		mockMvc.perform(get("/banking/userdetails"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].user_email", is("b@gmail.com")))
		.andExpect(jsonPath("$[0].acc_no", is("100000000006")))
		.andExpect(jsonPath("$[1].user_email", is("e@gmail.com")))
		.andExpect(jsonPath("$[1].user_email", is("100000000007")));
		
		verify(userService, times(1)).getUser();
	}

}
