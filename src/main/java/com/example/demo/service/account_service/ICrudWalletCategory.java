package com.example.demo.service.account_service;


import com.example.demo.Model.WalletCategory;

import java.util.List;

public interface ICrudWalletCategory {
    List<WalletCategory> findAll();
    WalletCategory findOne(Long id);
    void save(WalletCategory walletCategory);
    void delete(Long id);
}
