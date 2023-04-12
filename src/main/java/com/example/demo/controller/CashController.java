package com.example.demo.controller;
import com.example.demo.Model.Cash;
import com.example.demo.account.Account;
import com.example.demo.service.account_service.AccountService;
import com.example.demo.service.account_service.impl.CashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@CrossOrigin("*")
@RequestMapping("/cashes")
public class CashController {
    @Autowired
    public CashService cashService;
    @Autowired
    public AccountService accountService;
    @GetMapping("/{id}")
    public ResponseEntity<List<Cash>> findCashByID(@PathVariable Long id){
       return new ResponseEntity<>(cashService.findCashByIdUser(id), HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
    private ResponseEntity<Cash> findOne(@PathVariable Long id){
        return new ResponseEntity<>(cashService.findOne(id),HttpStatus.OK);
    }
    @PostMapping("/{userId}")
    private ResponseEntity<Void> create(@PathVariable Long userId,@RequestBody Cash cash){
        Account account=accountService.findAccountById(userId);
        cash.setAccount(account);
        cashService.save(cash);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/{id}")
    private ResponseEntity<Void> update(@PathVariable Long id,@RequestBody Cash cash){
        Cash cash1=cashService.findOne(id);
        if (cash1!=null){
            cash.setAccount(cash1.getAccount());
            cashService.save(cash);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable Long id){
        cashService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
