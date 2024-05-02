package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.exception.DaoException;
import com.techelevator.tenmo.model.Account;

public interface AccountDao {

    public double viewBalance(int id) throws DaoException;

    public void makeTransfer (int userId, double balance) throws DaoException;

    public Account getAccountByUser (int currentUserId) throws DaoException;

    public void insertTransferTable (int senderId, int receiverId, double amount) throws DaoException;
}
