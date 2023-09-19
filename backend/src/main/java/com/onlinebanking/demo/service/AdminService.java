package com.onlinebanking.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import com.onlinebanking.demo.entity.Admin;
import com.onlinebanking.demo.entity.User;
import com.onlinebanking.demo.repository.AdminRepository;

@Service
public class AdminService implements AdminServiceInterface {
	
	@Autowired
	AdminRepository adminRepo;
	
	private final BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
	
	@Override
	public Admin createAdmin(@Validated @RequestBody Admin admin)
	{
		 String hashPassword= passwordEncoder.encode(admin.getAdmin_pwd());
		admin.setAdmin_pwd(hashPassword);
		return adminRepo.save(admin);
	}

}
