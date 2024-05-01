package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class AccountController {
    AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping
    public double viewBalance(){
        return accountService.viewBalance();
    }
}
