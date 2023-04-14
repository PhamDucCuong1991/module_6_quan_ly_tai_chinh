package com.example.demo.service.account_service.impl;


import com.example.demo.Model.Cash;
import com.example.demo.repository.CashRepository;

import com.example.demo.service.account_service.ICrudCash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CashService implements ICrudCash {
    @Autowired
  private CashRepository cashRepository;


    @Override
    public List<Cash> findAll() {
        return cashRepository.findAll();
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
    public List<Cash> findCashByIdUser(Long id) {
        return cashRepository.findCashByUserId(id);
    }

    @Override
    public List<Cash> findCashByDate(Long userId, LocalDateTime startDate, LocalDateTime endDate) {
        return cashRepository.findCashByDateStart(userId,startDate,endDate);
    }
}
