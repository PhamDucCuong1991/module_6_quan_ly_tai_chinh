package com.example.demo.service.account_service;
import com.example.demo.Model.Wallet;
import java.util.List;
import java.util.Optional;
public interface ICrudWallet {
    List<Wallet> findAll(Optional<Long> userId);
    Wallet findOne(Long id);
    void save(Wallet wallet);
    void delete(Long id);
    Double sumMoney(Optional<Long> id);

}
