package com.example.demo.controller;


import com.example.demo.Model.Cash;
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

    @GetMapping
    public ResponseEntity<List<Cash>> findAll(){
       return new ResponseEntity<>(cashService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Cash> findOne(@PathVariable Long id){
        return new ResponseEntity<>(cashService.findOne(id),HttpStatus.OK);
    }
    @PostMapping
    private ResponseEntity<Void> create(@RequestBody Cash cash){
        cashService.save(cash);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/{id}")
    private ResponseEntity<Void> update(@PathVariable Long id,@RequestBody Cash cash){
        Cash cash1=cashService.findOne(id);
        if (cash1!=null){
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
