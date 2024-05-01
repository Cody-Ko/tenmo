package com.techelevator.tenmo.service;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.JdbcUserDao;
import com.techelevator.tenmo.security.SecurityUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class AccountService {

    AccountDao accountDao;
    JdbcTemplate jdbcTemplate;

    public AccountService(AccountDao accountDao, JdbcTemplate jdbcTemplate){
        this.accountDao = accountDao;
        this.jdbcTemplate = jdbcTemplate;
    }

    public double viewBalance(){
        JdbcUserDao jdbcUserDao = new JdbcUserDao(jdbcTemplate);
        String currentUser = SecurityUtils.getCurrentUsername().get();
        int currentUserId = jdbcUserDao.findIdByUsername(currentUser);
        return accountDao.viewBalance(currentUserId);
    }
}
