package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.exception.DaoException;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferDto;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("hasRole('USER')")

public class AccountController {
    AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping (path = "/balance")
    public double viewBalance(Principal user) {
        try {
            return accountService.viewBalance(user);
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

    @GetMapping (path = "/seetransfers")
    public List <Transfer> seeTransfers (Principal user){
       try{
           return accountService.seeTransfers(user);
       } catch (DaoException e){
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot view transfers");
       }
    }


}
