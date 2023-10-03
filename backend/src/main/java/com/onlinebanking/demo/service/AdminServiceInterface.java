package com.onlinebanking.demo.service;

import java.util.Optional;

import com.onlinebanking.demo.entity.Admin;


public interface AdminServiceInterface {
	
	Admin createAdmin(Admin admin);
	Optional<Admin>getByEmail(String admin_email);

}
