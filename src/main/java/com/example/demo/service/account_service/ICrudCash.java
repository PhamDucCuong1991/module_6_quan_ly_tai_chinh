package com.example.demo.service.account_service;



import com.example.demo.Model.Cash;

import java.util.List;

public interface ICrudCash {
    List<Cash> findAll();
    Cash findOne(Long id);
    void save(Cash cash);
    void delete(Long id);
    List<Cash> findCashByIdUser(Long id);

}
