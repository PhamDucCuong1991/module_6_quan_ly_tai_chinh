package com.example.demo.controller;
import com.example.demo.Model.Wallet;
import com.example.demo.account.Account;
import com.example.demo.account.service.AccountService;
import com.example.demo.service.ICrudWallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RestController
@CrossOrigin("*")
@RequestMapping("user{userId}/wallets")
public class  WalletController {
    @Autowired
    public ICrudWallet walletService;
    @Autowired
    public AccountService  accountService;
    @GetMapping()
    public ResponseEntity<Page<Wallet>> findAll(Pageable pageable,@PathVariable Optional<Long> userId){
        if (userId.isPresent()){
            return new ResponseEntity<>(walletService.findAll(pageable,userId.get()), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/page{index}")
    public ResponseEntity<Page<Wallet>> findAll(@PathVariable int index, @PathVariable Optional<Long> userId){
        if (userId.isPresent()){
            Pageable pageable= PageRequest.of(index,5);
            return new ResponseEntity<>(walletService.findAllPage(pageable,userId.get()), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/detail/{id}")
    private ResponseEntity<Wallet> findOne(@PathVariable Long userId,@PathVariable Long id){
        return new ResponseEntity<>(walletService.findOne(id),HttpStatus.OK);
    }
    @GetMapping("/total")
    private ResponseEntity<Double> sumMoney(@PathVariable Optional<Long> userId){
        if (userId.isPresent()){
            return new ResponseEntity<>(walletService.sumMoney(userId.get()),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping()
    private  ResponseEntity<Void> create(@PathVariable Optional<Long> userId,@RequestBody Wallet wallet){
        if (userId.isPresent()){
            Account account=accountService.findAccountById(userId.get());
            wallet.setAccount(account);
            walletService.save(wallet);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{id}")
    private ResponseEntity<Void> update(@PathVariable Long userId,@PathVariable Long id,@RequestBody Wallet wallet){
        Wallet wallet1=walletService.findOne(id);
        Account account=accountService.findAccountById(userId);
        if (wallet1!=null){
            wallet.setAccount(account);
            walletService.save(wallet);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable Long userId,@PathVariable Long id){
        walletService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
