package com.example.demo.repository;
import com.example.demo.Model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
@Transactional
@Repository
public interface WalletRepository extends JpaRepository<Wallet,Long> {
    @Query(value = "select w from Wallet w where w.account.id=:userId")
    List<Wallet> selectCash(@Param("userId") Optional<Long> userId);
    @Query(value = "select sum(w.totalMoney) from Wallet w where w.account.id=:userId")
    Double sumMoneyByUserId(@Param("userId")Optional<Long> userId);
}
