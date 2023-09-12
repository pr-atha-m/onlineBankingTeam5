package com.onlinebanking.demo.entity;


import java.util.Date;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "User_account")
public class User_account {
	
	private String user_email;
	private int acc_no;
	private String phone_no;
	private String father_name;
	private String aadhar_no;
	private Date dob;
	private String res_addr;
	private String perm_addr;
	private String occ_type;
	private float gross_annual_income;
	private String source_of_income;
	private boolean debit_status;
	private boolean net_banking;
	
	public User_account() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public User_account(String user_email, int acc_no, String phone_no, String father_name, String aadhar_no, Date dob,
			String res_addr, String perm_addr, String occ_type, float gross_annual_income, String source_of_income,
			boolean debit_status, boolean net_banking) {
		super();
		this.user_email = user_email;
		this.acc_no = acc_no;
		this.phone_no = phone_no;
		this.father_name = father_name;
		this.aadhar_no = aadhar_no;
		this.dob = dob;
		this.res_addr = res_addr;
		this.perm_addr = perm_addr;
		this.occ_type = occ_type;
		this.gross_annual_income = gross_annual_income;
		this.source_of_income = source_of_income;
		this.debit_status = debit_status;
		this.net_banking = net_banking;
	}
	
	@Id
	@Column(name = "user_email", nullable = false)
	@NotEmpty(message="You need to submit the email id")
	@Email(message="This is not a valid email format")
	public String getUser_email() {
		return user_email;
	}
	
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	

	@Column(name = "acc_no", nullable = false)
	@NotEmpty(message="Account number cannot be null")
	public int getAcc_no() {
		return acc_no;
	}

	public void setAcc_no(int acc_no) {
		this.acc_no = acc_no;
	}
	
	@Column(name = "phone_no", nullable = false)
	@NotEmpty(message="Phone number cannot be null")
	@Size(min=10,max=10, message="Phone number is invalid, it should be of 10 digits")
	public String getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}
	
	
	
	@Column(name = "father_name", nullable = false)
	public String getFather_name() {
		return father_name;
	}

	public void setFather_name(String father_name) {
		this.father_name = father_name;
	}
	
	@Column(name = "aadhar_no", nullable = false)
	@NotEmpty(message="You need to enter aadhar details")
	@Size(min=12,max=12,message="Aadhar is only 12 digits")
	public String getAadhar_no() {
		return aadhar_no;
	}

	public void setAadhar_no(String aadhar_no) {
		this.aadhar_no = aadhar_no;
	}

	@Column(name = "dob", nullable = false)
	@NotEmpty(message="Date of birth cannot be empty")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Column(name = "res_addr", nullable = false)
	@NotEmpty(message="You need to enter your residential address")
	public String getRes_addr() {
		return res_addr;
	}

	public void setRes_addr(String res_addr) {
		this.res_addr = res_addr;
	}
	
	@Column(name = "perm_addr", nullable = false)
	@NotEmpty(message="You need to enter your permanent address")
	public String getPerm_addr() {
		return perm_addr;
	}

	public void setPerm_addr(String perm_addr) {
		this.perm_addr = perm_addr;
	}
	
	@Column(name = "occ_type", nullable = false)
	public String getOcc_type() {
		return occ_type;
	}

	public void setOcc_type(String occ_type) {
		this.occ_type = occ_type;
	}
	
	@Column(name = "gross_annual_income", nullable = false)
	public float getGross_annual_income() {
		return gross_annual_income;
	}

	public void setGross_annual_income(float gross_annual_income) {
		this.gross_annual_income = gross_annual_income;
	}

	@Column(name = "source_of_income", nullable = false)
	public String getSource_of_income() {
		return source_of_income;
	}

	public void setSource_of_income(String source_of_income) {
		this.source_of_income = source_of_income;
	}
	
	@Column(name = "debitStatus", nullable = false)
	public boolean isDebit_status() {
		return debit_status;
	}

	public void setDebit_status(boolean debit_status) {
		this.debit_status = debit_status;
	}
	
	@Column(name = "net_banking", nullable = false)
	public boolean isNet_banking() {
		return net_banking;
	}

	public void setNet_banking(boolean net_banking) {
		this.net_banking = net_banking;
	}

}
