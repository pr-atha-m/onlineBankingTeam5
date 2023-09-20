package com.onlinebanking.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinebanking.demo.entity.Admin;
import com.onlinebanking.demo.entity.User;

public interface AdminRepository extends JpaRepository<Admin, String>{

	Admin save(Admin admin);

	

	

}
