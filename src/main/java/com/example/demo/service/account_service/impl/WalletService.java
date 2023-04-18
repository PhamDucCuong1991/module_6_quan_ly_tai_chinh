package com.example.demo.service.account_service.impl;
import com.example.demo.Model.Wallet;
import com.example.demo.repository.WalletRepository;
import com.example.demo.service.account_service.ICrudWallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class WalletService implements ICrudWallet {
    @Autowired
    public WalletRepository walletRepository;
    @Override
    public List<Wallet> findAll(Optional<Long> userID) {
        return walletRepository.selectCash(userID);
    }
    @Override
    public Wallet findOne(Long id) {
        return walletRepository.findById(id).orElse(null);
    }
    @Override
    public void save(Wallet wallet) {
    walletRepository.save(wallet);
    }
    @Override
    public void delete(Long id) {
    walletRepository.deleteById(id);
    }
    @Override
    public Double sumMoney(Optional<Long> id) {
        return walletRepository.sumMoneyByUserId(id);
    }
}
