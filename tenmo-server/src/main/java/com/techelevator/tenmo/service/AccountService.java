package com.techelevator.tenmo.service;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.exception.DaoException;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.security.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    // displays other users
    public List<User> userList (){
        List <User> currentList = userDao.findAll();
        String currentUserName = SecurityUtils.getCurrentUsername().get();
        User currentUser = userDao.findByUsername(currentUserName);
        currentList.remove(currentUser);
        return currentList;
    }
    @Transactional
    public void makeTransfer (int sendingUserId, int receivingUserId, double amountToSend) throws DaoException{

        double sendingUserBalance = accountDao.viewBalance(sendingUserId);
        double receivingUserBalance = accountDao.viewBalance(receivingUserId);

        if (sendingUserBalance >= amountToSend && amountToSend > 0){
            sendingUserBalance = sendingUserBalance - amountToSend;
            receivingUserBalance = receivingUserBalance + amountToSend;

            accountDao.makeTransfer(sendingUserId, sendingUserBalance);

            accountDao.makeTransfer(receivingUserId, receivingUserBalance);

          Account sendingAccount = accountDao.getAccountByUser(sendingUserId);

          Account receivingAccount = accountDao.getAccountByUser(receivingUserId);

          int sendingAccountId = sendingAccount.getAccountId();

          int receivingAccountId = receivingAccount.getAccountId();

          accountDao.insertTransferTable(sendingAccountId, receivingAccountId, amountToSend);


        }


    }


}
