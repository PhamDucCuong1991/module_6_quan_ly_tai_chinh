package com.example.demo.controller;

import com.example.demo.service.email_service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/sendEmail")
public class SendEmailController {
    @Autowired
    private EmailService emailService;

    @GetMapping()
    public ResponseEntity<Void> sendEmail(
            @RequestParam("to") String to,
            @RequestParam("subject") String subject,
            @RequestParam("text") String text) {
        String content="Chào Mừng Bạn Đã Đến Với App quản lý tài chính \n  mã xác nhận của bạn là  ";
        emailService.sendMail(to, subject, content+text);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}