package com.onlinebanking.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinebanking.demo.entity.User_account;


public interface UserAccountRepository extends JpaRepository<User_account, String> {

}
