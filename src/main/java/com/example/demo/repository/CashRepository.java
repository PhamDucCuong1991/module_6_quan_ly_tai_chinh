package com.example.demo.repository;
import com.example.demo.Model.Cash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Transactional
@Repository
public interface CashRepository extends JpaRepository<Cash,Long> {
@Query(value = "select c from Cash c where c.account.id=:userId")
    List<Cash> findCashByUserId(@Param("userId") Optional<Long> id);
@Query(value = "select c from Cash c where c.account.id=:userId and c.date>=:startDate and c.date<=:endDate")
    List<Cash> findCashByDateStart(@Param("userId")Long id, @Param("startDate")LocalDateTime starDate,@Param("endDate")LocalDateTime endDate);
}
