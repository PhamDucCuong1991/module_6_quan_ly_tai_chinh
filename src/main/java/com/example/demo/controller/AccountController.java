package com.example.demo.controller;
import com.example.demo.account.Account;
import com.example.demo.account.AccountToken;
import com.example.demo.account.Role;
import com.example.demo.account.service.AccountService;
import com.example.demo.config_sercurity.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/login")
    public AccountToken login(@RequestBody Account account) {
        if (!accountService.findAllByStatus().contains(accountService.findAccountByUserName(account.getUsername()))) {
            return null;
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtService.createToken(authentication);
        Account account1 = accountService.findAccountByUserName(account.getUsername());
        AccountToken accountToken = new AccountToken(account1.getId(), account1.getUsername(), account1.getAvatar(), token, account1.getRole());
        return accountToken;
    }
    @PostMapping("/register")
    public void register(@RequestBody Account account){
        Role role = new Role();
        role.setId(1L);
        account.setRole(role);
        account.setStatus(true);
        accountService.save(account);
    }

    @PutMapping("/update/{id}")
    private ResponseEntity<Void> updateProfile(@PathVariable Long id, @RequestBody Account account){
        Account account1=accountService.findAccountById(id);
        if (account1!=null){
            account.setId(id);
            accountService.save(account);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Account> findOne(@PathVariable Long id){
        return new ResponseEntity<>(accountService.findAccountById(id), HttpStatus.OK);
    }
}
