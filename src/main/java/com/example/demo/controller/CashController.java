package com.example.demo.controller;
import com.example.demo.Model.Cash;
import com.example.demo.Model.CashCategoryResult;
import com.example.demo.Model.Category;
import com.example.demo.account.Account;
import com.example.demo.account.service.AccountService;
import com.example.demo.service.ICrudCash;
import com.example.demo.service.ICrudWallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public ResponseEntity<Page<Cash>> findCashByID(@PageableDefault(value = 10000) Pageable pageable, @PathVariable Optional<Long> userId){
        if (userId.isPresent()){
            return new ResponseEntity<>(cashService.findCashByIdUser(pageable,userId.get()), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/ex")
    private ResponseEntity<List<Cash>> findCategoryEx(@PathVariable Optional<Long> userId){
        if (userId.isPresent()){
            return new ResponseEntity<>(cashService.findCashExpences(userId.get()),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/ex/category/{start}/{end}")
    private ResponseEntity<List<CashCategoryResult>> findAllByCategory(@PathVariable Optional<Long> userId,@PathVariable String start, @PathVariable String end){
        if (userId.isPresent()){
            LocalDate startDate=LocalDate.parse(start);
            LocalDate endDate=LocalDate.parse(end);
            return new ResponseEntity<>(cashService.findAllByCategory(userId.get(),startDate,endDate),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/ex/{start}/{end}")
    private ResponseEntity<Double> totalMoneyExpenseByTime(@PathVariable Optional<Long> userId,@PathVariable String start, @PathVariable String end){
        if (userId.isPresent()){
            LocalDate startDate=LocalDate.parse(start);
            LocalDate endDate=LocalDate.parse(end);
            return new ResponseEntity<>(cashService.totalMoneyExpenseByTime(userId.get(),startDate,endDate),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{start}/{end}")
    private ResponseEntity<Page<Cash>> findCashByDate(@PageableDefault(value = 5) Pageable pageable,@PathVariable Optional<Long> userId,
    @PathVariable String start, @PathVariable String end){
        if (userId.isPresent()){
            LocalDate startDate=LocalDate.parse(start);
            LocalDate endDate=LocalDate.parse(end);
            return new ResponseEntity<>(cashService.findCashByDate(pageable,userId.get(),startDate,endDate),HttpStatus.OK);
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
    private ResponseEntity<Void> update(@PathVariable Optional<Long> userId,@PathVariable Long id,@RequestBody Cash cash){
        if (userId.isPresent()){
            Cash cash1=cashService.findOne(id);
            Account account=accountService.findAccountById(userId.get());
            if (cash1!=null){
                cash.setAccount(account);
                cashService.save(cash);
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable Long userId,@PathVariable Long id){
        cashService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/{walletId}/page{index}")
    private ResponseEntity<Page<Cash>> findCashByWalletId(@PathVariable int index,@PathVariable Optional<Long> userId,@PathVariable Optional<Long> walletId,
                                                          @RequestParam Optional<String> start,@RequestParam Optional<String> end ){
        Pageable pageable= PageRequest.of(index,5);
        if (userId.isPresent()&&walletId.isPresent()&&start.isPresent()&&end.isPresent()){
            LocalDate startDate=LocalDate.parse(start.get());
            LocalDate  endDate=LocalDate.parse(end.get());
            return new ResponseEntity<>(cashService.findCashByIdWallet(pageable,userId.get(),walletId.get(),startDate,endDate),HttpStatus.OK);
        }else if (userId.isPresent()&&walletId.isPresent()){
            return new ResponseEntity<>(cashService.findCash(pageable,userId.get(),walletId.get()),HttpStatus.OK);
        }
   return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
