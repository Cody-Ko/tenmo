package com.techelevator.tenmo.exception;

public class DaoException extends Exception {

    public DaoException(String message, Exception cause){
        super(message, cause);
    }
}
