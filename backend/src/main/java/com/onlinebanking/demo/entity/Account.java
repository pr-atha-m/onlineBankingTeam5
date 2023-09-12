package com.onlinebanking.demo.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

public class Account {
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name=" user_email")
	private User user;

}
