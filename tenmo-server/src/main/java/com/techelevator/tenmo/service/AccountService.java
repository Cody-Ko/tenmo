package com.techelevator.tenmo.service;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.JdbcUserDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.security.SecurityUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class AccountService {

    AccountDao accountDao;
    UserDao userDao;

    public AccountService(AccountDao accountDao, UserDao userDao){
        this.accountDao = accountDao;
        this.userDao = userDao;
    }

    public double viewBalance(){
        String currentUser = SecurityUtils.getCurrentUsername().get();
        int currentUserId = userDao.findIdByUsername(currentUser);
        return accountDao.viewBalance(currentUserId);
    }
}
