package com.onlinebanking.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.onlinebanking.demo.entity.User_account;


public interface UserAccountRepository extends JpaRepository<User_account, String> {
 List<User_account> findByEmailId(String emailId);
 
}
