package com.example.demo.repository;
import com.example.demo.Model.Cash;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
@Transactional
@Repository
public interface CashRepository extends JpaRepository<Cash,Long> {
@Query(value = "select c from Cash c where c.account.id=:userId")
    Page<Cash> findCashByUserId(Pageable pageable, @Param("userId") Long id);
@Query(value = "select c from Cash c where c.account.id=:userId and c.date>=:startDate and c.date<=:endDate")
    Page<Cash> findCashByDateStart(Pageable pageable,@Param("userId")Long id, @Param("startDate")LocalDate starDate,@Param("endDate")LocalDate endDate);

@Query(value = "select c from Cash  c where c.account.id=:userId and c.wallet.id=:walletId and c.date=:startDate and c.date=:endDate")
    Page<Cash> findCashByWalletId(Pageable pageable,@Param("userId")Long userId ,@Param("walletId")Long walletId,@Param("startDate")LocalDate startDate,
                                  @Param("endDate")LocalDate endDate);
@Query(value = "select c from Cash c where c.account.id=:userId and c.wallet.id=:walletId")
    Page<Cash> searchCash(Pageable pageable,@Param("userId")Long userId,@Param("walletId")Long walletId);

//@Query(value = "select sum(c.money) from Cash c where c.account.id=:userId")
//    Double

}
