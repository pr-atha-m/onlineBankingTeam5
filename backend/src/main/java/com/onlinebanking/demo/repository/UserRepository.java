package com.onlinebanking.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinebanking.demo.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
	
	public Optional <User> findByEmail(String email);

}
