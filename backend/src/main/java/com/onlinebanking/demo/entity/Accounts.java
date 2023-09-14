package com.onlinebanking.demo.entity;
import java.util.Date;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;




@Entity
@Table(name = "Accounts")

public class Accounts {
	
	private String acc_no;
	private String acc_type;
	private String user_email;
	private int balance;
	private Date acc_open_date;
	public Accounts() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Accounts(String acc_no, String user_email, String acc_type, int balance, Date acc_open_date) {
		super();
		this.acc_no = acc_no;
		this.user_email = user_email;
		this.acc_type = acc_type;
		this.balance = balance;
		this.acc_open_date = acc_open_date;
	}

	
	
	@Id
	@Column(name = "acc_no", nullable = false)
	@NotEmpty(message="You need to submit the account number")
	public String getAcc_no() {
		return acc_no;
	}

	public void setAcc_no(String acc_no) {
		this.acc_no = acc_no;
	}

	
	@Column(name = "user_email", nullable = false)
	@NotEmpty(message="You need to submit the email id")
	@Email(message="This is not a valid email format")
	public String getUser_email() {
		return user_email;
	}
	
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	
	@Column(name = "acc_type", nullable = false)
	@NotEmpty(message="Account type cannont be empty")
	public String getAcc_type() {
		return acc_type;
	}

	public void setAcc_type(String acc_type) {
		this.acc_type = acc_type;
	}

	@Column(name = "acc_balance", nullable = false)
	@NotEmpty(message="Balance field Can't be empty")
	
	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	
	
	@Column(name = "acc_open_date", nullable = false)
	@NotEmpty(message="Account Open Date is required!")
	
	public Date getAcc_open_date() {
		return acc_open_date;
	}

	public void setAcc_open_date(Date acc_open_date) {
		this.acc_open_date = acc_open_date;
	}

	
	
	//This implies that 1 user can have many accounts.
//	@OneToMany(cascade=CascadeType.ALL)
//	private Set<Account> accounts
	
	
	
	

}
