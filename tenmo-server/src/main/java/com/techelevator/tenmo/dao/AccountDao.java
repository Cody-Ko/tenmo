package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.exception.DaoException;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface AccountDao {

    public double viewBalance(int id) throws DaoException;

    public void makeTransfer (int userId, double balance) throws DaoException;

    public Account getAccountByUser (int currentUserId) throws DaoException;

    public void insertTransferTable (int senderId, int receiverId, double amount) throws DaoException;

    public List<Transfer> seeTransfers (int userId) throws DaoException;
}
