package com.example.demo.service.account_service;
import com.example.demo.Model.Plan;

import java.util.List;
import java.util.Optional;
public interface ICrudPlan {
    List<Plan> findAll(Optional<Long> userId);
    Plan findOne(Long id);
    void save(Plan plan);
    void delete(Long id);

}
