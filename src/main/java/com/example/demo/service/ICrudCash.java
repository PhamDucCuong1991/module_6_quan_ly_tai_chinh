package com.example.demo.service;
import com.example.demo.Model.Cash;
import com.example.demo.Model.CashCategoryResult;
import com.example.demo.Model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
public interface ICrudCash extends ICrud<Cash> {
    Cash findOne(Long id);
    void save(Cash cash);
    void delete(Long id);
    List<Cash> findCashExpences(Long userId);
    List<CashCategoryResult> findAllByCategory(Long userId, LocalDate startDate, LocalDate endDate);
    Double totalMoneyExpenseByTime(Long userId, LocalDate startDate, LocalDate endDate);
    Page<Cash> findCashByIdUser(Pageable pageable, Long userId);
    Page<Cash> findCashByDate(Pageable pageable,Long userId, LocalDate startDate, LocalDate endDate);
    Page<Cash> findCashByIdWallet(Pageable pageable,Long userId,Long walletId,LocalDate startDate,LocalDate endDate);
    Page<Cash> findCash(Pageable pageable,Long userId,Long walletID);
    List<Cash> findCashByWallet( Long walletId);
}
