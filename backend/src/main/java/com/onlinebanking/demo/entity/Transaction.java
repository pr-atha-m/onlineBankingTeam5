package com.onlinebanking.demo.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name="Transaction_table")
public class Transaction {
	
	private int trans_id;
	private String sender_account;
	private String receiver_account;
	private String amount;
	private String trans_date;
	private String maturity_remarks;
	private String trans_mode;
	private String instructions;

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Transaction(int trans_id, String sender_account, String receiver_account, String amount, String trans_date,
			String maturity_remarks, String trans_mode, String instructions) {
		super();
		this.trans_id = trans_id;
		this.sender_account = sender_account;
		this.receiver_account = receiver_account;
		this.amount = amount;
		this.trans_date = trans_date;
		this.maturity_remarks = maturity_remarks;
		this.trans_mode = trans_mode;
		this.instructions=instructions;
	}
	
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="trans_id",nullable=false)
	public int getTrans_id() {
		return trans_id;
	}
	public void setTrans_id(int trans_id) {
		this.trans_id = trans_id;
	}
	
	@Column(name="sender_account", nullable=false)
	@NotEmpty(message=" Sender account number cannot be empty")
	public String getSender_account() {
		return sender_account;
	}
	public void setSender_account(String sender_account) {
		this.sender_account = sender_account;
	}
	
	@Column(name="receiver_account", nullable=false)
	@NotEmpty(message=" Receiver account number cannot be empty")
	public String getReceiver_account() {
		return receiver_account;
	}
	public void setReceiver_account(String receiver_account) {
		this.receiver_account = receiver_account;
	}
	
	@Column(name="amount", nullable=false)
	@NotEmpty(message="You need to enter an amount")
	@DecimalMax(value="100000.0")
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="date",  nullable=false)
	public String getTrans_date() {
		return trans_date;
	}
	public void setTrans_date(String trans_date) {
		this.trans_date = trans_date;
	}
	
	@Column(name="maturity_remarks", nullable=false)
	public String getMaturity_remarks() {
		return maturity_remarks;
	}
	public void setMaturity_remarks(String maturity_remarks) {
		this.maturity_remarks = maturity_remarks;
	}
	
	@Column(name="trans_mode", nullable=false)
	public String getTrans_mode() {
		return trans_mode;
	}
	public void setTrans_mode(String trans_mode) {
		this.trans_mode = trans_mode;
	}
	
	@Column(name="instructions", nullable=false)
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	
	

}
