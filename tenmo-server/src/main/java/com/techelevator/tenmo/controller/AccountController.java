package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.exception.DaoException;
import com.techelevator.tenmo.model.TransferDto;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

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
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot display current balance");
        }
    }

    @PostMapping (path = "/transfer")
    public void makeTransfer(@RequestBody TransferDto transferDto){
        try{
            accountService.makeTransfer(transferDto.getSenderId(), transferDto.getReceiverId(), transferDto.getAmount());
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot make transfer");
        }
    }

    @GetMapping(path = "/users")
    public List<User> listUsers() {
        return accountService.userList();
    }

}
