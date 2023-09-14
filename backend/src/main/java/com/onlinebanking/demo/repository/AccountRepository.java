package com.onlinebanking.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.onlinebanking.demo.entity.Accounts;


public interface AccountRepository extends JpaRepository<Accounts, String> {

}


