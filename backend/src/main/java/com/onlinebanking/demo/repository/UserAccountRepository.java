package com.onlinebanking.demo.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.onlinebanking.demo.entity.User_account;


public interface UserAccountRepository extends JpaRepository<User_account,Integer> {
 Set<User_account>findByUser_user_email(String user_email);
}