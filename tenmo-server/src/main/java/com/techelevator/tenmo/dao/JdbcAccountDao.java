package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.exception.DaoException;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.rowmapper.TransferRowMapper;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class JdbcAccountDao implements AccountDao{


    public JdbcAccountDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    private JdbcTemplate jdbcTemplate;


    public double viewBalance(int currentUserId) throws DaoException {
        String sql = "SELECT balance FROM account WHERE user_id = ?";
        try {
            // if statement to avoid null pointer exception?
            // switch from double to BigDecimal
            double balance = jdbcTemplate.queryForObject(sql, Double.class, currentUserId);
            return balance;
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }
    }

    public Account getAccountByUser(int currentUserId) throws DaoException {
        String sql = "SELECT * FROM account WHERE user_id = ?";
        try {
            // if statement to avoid null pointer exception?
            // switch from double to BigDecimal
            SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, currentUserId);
            if (rs.next()){
                return mapAccount(rs);
            } else {
                return null;
            }
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }
    }

    // any feedback to user? i.e confirmation.
    public void makeTransfer (int userId, double balance) throws DaoException {

        String sql = "UPDATE account SET balance = ? WHERE user_id = ?;";

        try {
            int numberOfRows = jdbcTemplate.update(sql, balance, userId);

        } catch (Exception e){
            throw new DaoException("Unable to make transfer", e);
        }
    }



    public void insertTransferTable (int senderId, int receiverId, double amount) throws DaoException{
        String sql = "INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount ) VALUES (?, ?, ?, ?, ?)";

        try {
            jdbcTemplate.update(sql, 2, 2, senderId, receiverId, amount);

        } catch (Exception e){
            throw new DaoException("Unable to log transfer", e);
        }
    }

    public List <Transfer> seeTransfers(int currentUserId) throws DaoException {

        String sql = "SELECT * FROM transfer JOIN account ON account_id = account_from WHERE user_id = ?";

        try{
            List<Transfer> transferList = jdbcTemplate.query(sql, new TransferRowMapper(), currentUserId);
            return transferList;
        } catch (Exception e){
            throw new DaoException("Unable to log transfer", e);
        }



    }



    public Account mapAccount(SqlRowSet rs){
        Account account = new Account();
        account.setAccountId(rs.getInt("account_id"));
        account.setUserId(rs.getInt("user_id"));
        account.setBalance(rs.getDouble("balance"));
            return account;
    }

}
