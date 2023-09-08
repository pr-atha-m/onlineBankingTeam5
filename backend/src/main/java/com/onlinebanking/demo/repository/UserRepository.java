package com.onlinebanking.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinebanking.demo.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}
