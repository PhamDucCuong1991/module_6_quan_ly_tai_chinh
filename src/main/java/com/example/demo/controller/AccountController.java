package com.example.demo.controller;

import com.example.demo.account.Account;
import com.example.demo.account.AccountToken;
import com.example.demo.service.account_service.AccountService;
import com.example.demo.service.account_service.JwtService;
import com.example.demo.service.email_service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private EmailService emailService;

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
    public boolean register(@RequestBody Account account) {

        if (accountService.checkRegister(account.getUsername())) {
            account.setStatus(false);
            accountService.save(account);
            String link = "http://localhost:3000/confirm-email/" + account.getId();
            String to = account.getUsername();
            String subject = "Register success!";
            String text = "Please click this link to confirm: " + link;
            emailService.sendMail(to, subject, text);
            return true;
        }

        return false;
    }

    @GetMapping("/confirm/{id}")
    public void confirm(@PathVariable Long id) {
        Account account = accountService.findAccountById(id);
        account.setStatus(true);
        accountService.save(account);

    }

    @GetMapping("/{id}")
    public Account findOne(@PathVariable Long id) {
        return accountService.findAccountById(id);
    }

    @PutMapping("/changePassword")
    public void changePassword(@RequestBody Account account) {
        Account account1 = accountService.findAccountById(account.getId());
        account1.setPassword(account.getPassword());
        accountService.save(account1);
    }

    @PutMapping("/update/{id}")
    public boolean update(@RequestBody Account account, @PathVariable Long id) {
        if (accountService.findAccountById(id).isStatus()) {
            accountService.save(account);
            return true;
        }
        return false;
    }

}
