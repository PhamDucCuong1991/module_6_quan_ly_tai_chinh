package com.example.demo.controller;
import com.example.demo.Model.Wallet;
import com.example.demo.service.account_service.impl.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@CrossOrigin("*")
@RequestMapping("/wallets")
public class WalletController {
    @Autowired
    public WalletService walletService;
    @GetMapping
    public ResponseEntity<List<Wallet>> findAll(){
        return new ResponseEntity<>(walletService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    private ResponseEntity<Wallet> findOne(@PathVariable Long id){
        return new ResponseEntity<>(walletService.findOne(id),HttpStatus.OK);
    }
    @PostMapping
    private  ResponseEntity<Void> create(@RequestBody Wallet wallet){
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
