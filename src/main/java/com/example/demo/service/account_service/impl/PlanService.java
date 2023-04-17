package com.example.demo.service.account_service.impl;
import com.example.demo.Model.Plan;
import com.example.demo.repository.PlanRepository;
import com.example.demo.service.account_service.ICrudPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class PlanService implements ICrudPlan {
    @Autowired
    public PlanRepository planRepository;
    @Override
    public List<Plan> findAll(Optional<Long> userId) {
        return planRepository.findAllPlan(userId);
    }
    @Override
    public Plan findOne(Long id) {
        return planRepository.findById(id).orElse(null);
    }
    @Override
    public void save(Plan plan) {
    planRepository.save(plan);
    }
    @Override
    public void delete(Long id) {
    planRepository.deleteById(id);
    }
}
