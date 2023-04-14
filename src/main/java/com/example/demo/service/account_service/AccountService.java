package com.example.demo.service.account_service;


import com.example.demo.account.Account;
import com.example.demo.repository.AccountRepository;
import com.example.demo.service.email_service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private EmailService emailService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = accountRepository.findAccountByUsername(username);

        return new User(account.getUsername(),account.getPassword(), account.getAuthorities());
    }

    public Account findAccountByUserName(String username){
        return accountRepository.findAccountByUsername(username);
    }

    public Account findAccountById(Long id){
        return accountRepository.findAccountById(id);
    }
    public void save(Account account){
        accountRepository.save(account);
    }

    public boolean checkRegister(String username){
        if(accountRepository.findAccountByUsername(username)!=null){
            return false;
        }
        return true;
    }

    public List<Account> findAll(){
        return accountRepository.findAll();
    }

    public List<Account> findAllByStatus(){
        return accountRepository.findAllByStatus();
    }

    public void deleteById(Long id){
        accountRepository.deleteById(id);
    }


}
