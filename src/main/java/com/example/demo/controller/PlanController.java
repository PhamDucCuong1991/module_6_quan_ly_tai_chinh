package com.example.demo.controller;


import com.example.demo.Model.Plan;
import com.example.demo.service.account_service.impl.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/plans")
public class PlanController {
    @Autowired
    public PlanService planService;
    @GetMapping
    private ResponseEntity<List<Plan>> findAll(){
        return new ResponseEntity<>(planService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    private ResponseEntity<Plan> findOne(@PathVariable Long id){
        return new ResponseEntity<>(planService.findOne(id),HttpStatus.OK);
    }
    @PostMapping
    private ResponseEntity<Void> create(@RequestBody Plan plan){
        planService.save(plan);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @PutMapping("/{id}")
    private ResponseEntity<Void> update(@PathVariable Long id,@RequestBody Plan plan){
        Plan plan1=planService.findOne(id);
        if (plan1!=null){
            planService.save(plan);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable Long id){
        planService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
