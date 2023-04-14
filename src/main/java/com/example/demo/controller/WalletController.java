package com.example.demo.controller;
import com.example.demo.Model.Wallet;
import com.example.demo.account.Account;
import com.example.demo.service.account_service.AccountService;
import com.example.demo.service.account_service.ICrudAccount;
import com.example.demo.service.account_service.ICrudPlan;
import com.example.demo.service.account_service.impl.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@CrossOrigin("*")
@RequestMapping("/wallets")
public class WalletController {
    @Autowired
    public WalletService walletService;
    @Autowired
    public AccountService  accountService;
    @GetMapping("/userId")
    public ResponseEntity<List<Wallet>> findAll(@PathVariable Long userId){
        return new ResponseEntity<>(walletService.findAll(userId), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    private ResponseEntity<Wallet> findOne(@PathVariable Long id){
        return new ResponseEntity<>(walletService.findOne(id),HttpStatus.OK);
    }
    @GetMapping("/total/{userId}")
    private ResponseEntity<Double> sumMoney(@PathVariable Long userId){
        return new ResponseEntity<>(walletService.sumMoney(userId),HttpStatus.OK);
    }
    @PostMapping("/userId")
    private  ResponseEntity<Void> create(@PathVariable Long userId,@RequestBody Wallet wallet){
        Account account=accountService.findAccountById(userId);
        wallet.setAccount(account);
        walletService.save(wallet);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @PutMapping("/{id}")
    private ResponseEntity<Void> update(@PathVariable Long id,@RequestBody Wallet wallet){
        Wallet wallet1=walletService.findOne(id);
        if (wallet1!=null){
            walletService.save(wallet);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable Long id){
        walletService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
