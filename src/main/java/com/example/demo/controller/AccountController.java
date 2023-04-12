package com.example.demo.controller;
import com.example.demo.account.Account;
import com.example.demo.account.AccountToken;
import com.example.demo.account.Role;
import com.example.demo.service.account_service.AccountService;
import com.example.demo.service.account_service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
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
    @GetMapping("/{id}")
    public Account findOne(@PathVariable Long id){
        return accountService.findAccountById(id);
    }
}
