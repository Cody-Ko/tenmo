package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.exception.DaoException;

public interface AccountDao {

    public double viewBalance(int id) throws DaoException;
}
