package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.exception.DaoExceptions;

import java.security.Principal;

public interface AccountDao {

    public double viewBalance(int id) throws DaoExceptions;
}
