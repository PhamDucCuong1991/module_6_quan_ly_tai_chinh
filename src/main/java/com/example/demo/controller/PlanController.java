package com.example.demo.controller;
import com.example.demo.Model.Plan;
import com.example.demo.account.Account;
import com.example.demo.service.account_service.AccountService;
import com.example.demo.service.account_service.impl.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/user{userId}/plans")
public class PlanController {
    @Autowired
    public PlanService planService;
    @Autowired
    public AccountService accountService;
    @GetMapping
    private ResponseEntity<List<Plan>> findAll(@PathVariable Optional<Long> userId){
        if (userId.isPresent()){
            return new ResponseEntity<>(planService.findAll(userId), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{id}")
    private ResponseEntity<Plan> findOne(@PathVariable Long userId,@PathVariable Long id){
        return new ResponseEntity<>(planService.findOne(id),HttpStatus.OK);
    }
    @PostMapping
    private ResponseEntity<Void> create(@PathVariable Long userId,@RequestBody Plan plan){
       Account account= accountService.findAccountById(userId);
       plan.setAccount(account);
        planService.save(plan);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    private ResponseEntity<Void> update(@PathVariable Long userId,@PathVariable Long id,@RequestBody Plan plan){
        Plan plan1=planService.findOne(id);
        if (plan1!=null){
            planService.save(plan);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable Long userId,@PathVariable Long id){
        planService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
