package com.example.demo.service.impl;
import com.example.demo.Model.Wallet;
import com.example.demo.repository.WalletRepository;
import com.example.demo.service.ICrudWallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class WalletService implements ICrudWallet {
    @Autowired
    public WalletRepository walletRepository;
    @Override
    public List<Wallet> findAll(Long userID) {
//        return walletRepository.selectCash(userID);
        return null;
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
    public Double sumMoney(Long id) {
        return walletRepository.sumMoneyByUserId(id);
    }
    @Override
    public Wallet findWalletByUserId(Long userId, Long walletId) {
        return walletRepository.findWalletByIdAccount(userId,walletId);
    }

    @Override
    public Page<Wallet> findAllPage(Pageable pageable,Long userId) {
        return walletRepository.selectCash(pageable,userId);
    }
}
