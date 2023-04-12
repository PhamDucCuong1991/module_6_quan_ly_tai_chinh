package com.example.demo.repository;
import com.example.demo.Model.Cash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Transactional
@Repository
public interface CashRepository extends JpaRepository<Cash,Long> {
@Query(value = "select c from Cash c where c.account.id=:userId")
    List<Cash> findCashByUserId(@Param("userId")Long id);

}
