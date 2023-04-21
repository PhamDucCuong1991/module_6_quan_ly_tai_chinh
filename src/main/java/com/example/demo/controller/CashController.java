package com.example.demo.controller;
import com.example.demo.Model.Cash;
import com.example.demo.account.Account;
import com.example.demo.account.service.AccountService;
import com.example.demo.service.ICrudCash;
import com.example.demo.service.ICrudWallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@RestController
@CrossOrigin("*")
@RequestMapping("/user{userId}/cashes")
public class CashController {
    @Autowired
    public ICrudCash cashService;
    @Autowired
    public AccountService accountService;
    @Autowired
    public ICrudWallet walletService;
    @GetMapping()
    public ResponseEntity<Page<Cash>> findCashByID(@PageableDefault(value = 5) Pageable pageable, @PathVariable Optional<Long> userId){
        if (userId.isPresent()){
            return new ResponseEntity<>(cashService.findCashByIdUser(pageable,userId.get()), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{start}/{end}")
    private ResponseEntity<List<Cash>> findCashByDate(@PathVariable Optional<Long> userId,
    @PathVariable String start, @PathVariable String end){
        if (userId.isPresent()){
            LocalDate startDate=LocalDate.parse(start);
            LocalDate endDate=LocalDate.parse(end);
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
            cash.setAccount(account);
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
    @GetMapping("/{walletId}")
    private ResponseEntity<List<Cash>> findCashByWalletId(@PathVariable Optional<Long> userId,@PathVariable Optional<Long> walletId,
                                                          @RequestParam Optional<String> start,@RequestParam Optional<String> end ){
        if (userId.isPresent()&&walletId.isPresent()&&start.isPresent()&&end.isPresent()){
            LocalDate startDate=LocalDate.parse(start.get());
            LocalDate  endDate=LocalDate.parse(end.get());
            return new ResponseEntity<>(cashService.findCashByIdWallet(userId.get(),walletId.get(),startDate,endDate),HttpStatus.OK);
        }else if (userId.isPresent()&&walletId.isPresent()){

            return new ResponseEntity<>(cashService.findCash(userId.get(),walletId.get()),HttpStatus.OK);
        }
   return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
