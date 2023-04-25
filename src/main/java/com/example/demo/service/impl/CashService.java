package com.example.demo.service.impl;
import com.example.demo.Model.Cash;
import com.example.demo.repository.CashRepository;
import com.example.demo.service.ICrudCash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
@Service
public class CashService implements ICrudCash {
    @Autowired
  private CashRepository cashRepository;

    @Override
    public List<Cash> findAll(Long userId) {
        return null;
    }
    @Override
    public Cash findOne(Long id) {
        return cashRepository.findById(id).orElse(null);
    }
    @Override
    public void save(Cash cash) {
        cashRepository.save(cash);
    }
    @Override
    public void delete(Long id) {
    cashRepository.deleteById(id);
    }
    @Override
    public Page<Cash> findCashByIdUser(Pageable pageable, Long userId) {
        return cashRepository.findCashByUserId(pageable,userId);
    }

    @Override
    public Page<Cash> findCashByDate(Pageable pageable,Long userId, LocalDate startDate, LocalDate endDate) {
        return cashRepository.findCashByDateStart(pageable,userId,startDate,endDate);
    }
    @Override
    public Page<Cash> findCashByIdWallet(Pageable pageable,Long userId, Long walletId, LocalDate startDate, LocalDate endDate) {
            return cashRepository.findCashByWalletId(pageable,userId,walletId,startDate,endDate);
    }
    public List<Cash> findCashByWallet( Long walletId) {
        return cashRepository.findCashByWallet(walletId);
    }
    @Override
    public Page<Cash> findCash(Pageable pageable,Long userId, Long walletID) {
        return cashRepository.searchCash(pageable,userId,walletID);
    }
}
