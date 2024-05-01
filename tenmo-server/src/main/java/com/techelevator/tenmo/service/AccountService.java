package com.techelevator.tenmo.service;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.JdbcUserDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.exception.DaoExceptions;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.security.SecurityUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    AccountDao accountDao;
    UserDao userDao;

    public AccountService(AccountDao accountDao, UserDao userDao){
        this.accountDao = accountDao;
        this.userDao = userDao;
    }

    public double viewBalance() throws DaoExceptions {
        String currentUser = SecurityUtils.getCurrentUsername().get();
        try{
            int currentUserId = userDao.findIdByUsername(currentUser);
            return accountDao.viewBalance(currentUserId);
        } catch (Exception e){
            throw new DaoExceptions("Error could not display current balance", e);
        }

    }

    public List<User> userList (){
        List <User> currentList = userDao.findAll();
        String currentUserName = SecurityUtils.getCurrentUsername().get();
        User currentUser = userDao.findByUsername(currentUserName);
        currentList.remove(currentUser);
        return currentList;
    }


}
