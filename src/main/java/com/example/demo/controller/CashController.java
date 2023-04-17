package com.example.demo.controller;
import com.example.demo.Model.Cash;
import com.example.demo.account.Account;
import com.example.demo.service.account_service.AccountService;
import com.example.demo.service.account_service.impl.CashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@RestController
@CrossOrigin("*")
@RequestMapping("/user{userId}/cashes")
public class CashController {
    @Autowired
    public CashService cashService;
    @Autowired
    public AccountService accountService;
    @GetMapping()
    public ResponseEntity<List<Cash>> findCashByID(@PathVariable Optional<Long> userId){
        if (userId.isPresent()){
            return new ResponseEntity<>(cashService.findCashByIdUser(userId), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{start}/{end}")
    private ResponseEntity<List<Cash>> findCashByDate(@PathVariable Optional<Long> userId,
    @PathVariable String start, @PathVariable String end){
        if (userId.isPresent()){
            LocalDateTime startDate=LocalDateTime.parse(start);
            LocalDateTime endDate=LocalDateTime.parse(end);
            return new ResponseEntity<>(cashService.findCashByDate(userId.get(),startDate,endDate),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/detail/{id}")
    private ResponseEntity<Cash> findOne(@PathVariable Long userId,@PathVariable Long id){
        return new ResponseEntity<>(cashService.findOne(id),HttpStatus.OK);
    }
    @PostMapping()
    private ResponseEntity<Void> create(@PathVariable Optional<Long> userId,@RequestBody Cash cash){
        if (userId.isPresent()){
            Account account=accountService.findAccountById(userId.get());
            cashService.save(cash);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PutMapping("/{id}")
    private ResponseEntity<Void> update(@PathVariable Long userId,@PathVariable Long id,@RequestBody Cash cash){
        Cash cash1=cashService.findOne(id);
        if (cash1!=null){
            cash.setAccount(cash1.getAccount());
            cashService.save(cash);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable Long userId,@PathVariable Long id){
        cashService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
