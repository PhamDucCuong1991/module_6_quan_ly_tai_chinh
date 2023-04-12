package com.example.demo.controller;


import com.example.demo.Model.WalletCategory;
import com.example.demo.service.account_service.impl.WalletCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@CrossOrigin("*")
@RequestMapping("/wCategory")
public class WalletCategoryController {
    @Autowired
    public WalletCategoryService walletCategoryService;
    @GetMapping
    private ResponseEntity<List<WalletCategory>> findAll(){
        return new ResponseEntity<>(walletCategoryService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    private ResponseEntity<WalletCategory> findOne(@PathVariable Long id){
        return new ResponseEntity<>(walletCategoryService.findOne(id),HttpStatus.OK);
    }
    @PostMapping
    private ResponseEntity<Void> create(@RequestBody WalletCategory walletCategory){
        walletCategoryService.save(walletCategory);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @PutMapping("{id}")
    private ResponseEntity<Void> update(@PathVariable Long id,@RequestBody WalletCategory walletCategory){
        WalletCategory walletCategory1=walletCategoryService.findOne(id);
        if(walletCategory1!=null){
            walletCategoryService.save(walletCategory);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable Long id){
        walletCategoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
