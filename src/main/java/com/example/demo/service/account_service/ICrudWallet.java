package com.example.demo.service.account_service;



import com.example.demo.Model.Wallet;

import java.util.List;

public interface ICrudWallet {
    List<Wallet> findAll();
    Wallet findOne(Long id);
    void save(Wallet wallet);
    void delete(Long id);

}
