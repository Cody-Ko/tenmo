package com.techelevator.tenmo.service;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.exception.DaoException;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.security.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    AccountDao accountDao;
    UserDao userDao;

    public AccountService(AccountDao accountDao, UserDao userDao){
        this.accountDao = accountDao;
        this.userDao = userDao;
    }

    public double viewBalance() throws DaoException {
        String currentUser = SecurityUtils.getCurrentUsername().get();
            int currentUserId = userDao.findIdByUsername(currentUser);
            return accountDao.viewBalance(currentUserId);
    }

    public List<User> userList (){
        List <User> currentList = userDao.findAll();
        String currentUserName = SecurityUtils.getCurrentUsername().get();
        User currentUser = userDao.findByUsername(currentUserName);
        currentList.remove(currentUser);
        return currentList;
    }


}
