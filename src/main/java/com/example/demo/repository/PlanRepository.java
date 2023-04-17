package com.example.demo.repository;
import com.example.demo.Model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
@Transactional
@Repository
public interface PlanRepository extends JpaRepository<Plan,Long> {
    @Query(value = "select p from Plan p where p.account.id=:userId")
    List<Plan> findAllPlan (@Param("userId") Optional<Long> userId);
}
