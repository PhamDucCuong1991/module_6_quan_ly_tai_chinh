package com.example.demo.service.account_service.impl;

import com.example.demo.model.WalletCategory;
import com.example.demo.repository.WalletCategoryRepository;
import com.example.demo.service.ICrudWalletCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletCategoryService implements ICrudWalletCategory {
    @Autowired
    public WalletCategoryRepository walletCategoryRepository;

    @Override
    public List<WalletCategory> findAll() {
        return walletCategoryRepository.findAll();
    }

    @Override
    public WalletCategory findOne(Long id) {
        return walletCategoryRepository.findById(id).orElse(null);
    }

    @Override
    public void save(WalletCategory walletCategory) {
    walletCategoryRepository.save(walletCategory);
    }

    @Override
    public void delete(Long id) {
    walletCategoryRepository.deleteById(id
    );
    }
}
