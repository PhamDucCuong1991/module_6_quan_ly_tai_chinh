package com.example.demo.service;
import com.example.demo.Model.Wallet;
import java.util.List;
public interface ICrudWallet extends ICrud<Wallet>{
    List<Wallet> findAll(Long userId);
    Wallet findOne(Long id);
    void save(Wallet wallet);
    void delete(Long id);
    Double sumMoney(Long id);
    Wallet findWalletByUserId(Long userId,Long walletId);
}
