package com.example.demo.service.account_service.impl;
import com.example.demo.Model.Cash;
import com.example.demo.repository.CashRepository;
import com.example.demo.service.account_service.ICrudCash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
public class CashService implements ICrudCash {
    @Autowired
  private CashRepository cashRepository;
    @Override
    public Page<Cash> findAll(Pageable pageable) {
        return cashRepository.findAll(pageable);
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
    public Page<Cash> findCashByIdUser(Pageable pageable, Optional<Long> userId) {
        return cashRepository.findCashByUserId(pageable,userId);
    }
    @Override
    public Page<Cash> findCashByDate(Pageable pageable,Long userId, LocalDate startDate, LocalDate endDate) {
        return cashRepository.findCashByDateStart(pageable,userId,startDate,endDate);
    }
}
