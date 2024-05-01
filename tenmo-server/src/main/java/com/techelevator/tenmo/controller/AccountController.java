package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RestController
public class AccountController {
    AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping
    public double viewBalance() {
        try {
            return accountService.viewBalance();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot display current balance");
        }
    }
}
