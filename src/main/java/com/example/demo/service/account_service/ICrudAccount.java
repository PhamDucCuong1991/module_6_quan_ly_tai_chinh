package com.example.demo.service.account_service;
import com.example.demo.account.Account;

import java.util.List;
public interface ICrudAccount {
    List<Account> findAll();
    Account findOne(Long id);
    void save(Account account);
    void delete(Long id);
    Account login(String username,String password);
}
