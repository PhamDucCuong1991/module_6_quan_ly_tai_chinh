package com.example.demo.service.account_service;
import com.example.demo.Model.Cash;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
public interface ICrudCash {
    Page<Cash> findAll(Pageable pageable);
    Cash findOne(Long id);
    void save(Cash cash);
    void delete(Long id);
    Page<Cash> findCashByIdUser(Pageable pageable, Optional<Long> userId);
    List<Cash> findCashByDate(Long userId, LocalDate startDate, LocalDate endDate);

}
