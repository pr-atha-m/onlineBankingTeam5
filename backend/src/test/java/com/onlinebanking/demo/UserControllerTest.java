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

import java.util.Arrays;


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
import com.onlinebanking.demo.entity.User;
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
	
	private List<User> users;
	
	@BeforeEach
	public void init()
	{
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
		
		User user1 = new User("joy", "dbfjgbeb","e@gmail.com","1224556678");
		User user2 = new User("j", "b@gmail.com","123445678","pugrohit");
		User user3 = new User("pratham", "c@gmail.com","123456768","shah");
		User user4 = new User("salman", "d@gmail.com","12344567d48","joshi");
		
		users = Arrays.asList(user1, user2, user3, user4);
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
		.andExpect(jsonPath("$[0].user_email", is("e@gmail.com")));
		
		verify(userService, times(1)).getUser();
	}

}
