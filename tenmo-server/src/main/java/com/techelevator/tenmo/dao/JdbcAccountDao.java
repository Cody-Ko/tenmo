package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.exception.DaoExceptions;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.RowSet;
import java.math.BigDecimal;
import java.security.Principal;
@Component
public class JdbcAccountDao implements AccountDao{


    public JdbcAccountDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    private JdbcTemplate jdbcTemplate;


    public double viewBalance(int currentUserId) throws DaoExceptions {
        String sql = "SELECT balance FROM account WHERE user_id = ?";
        try {
            // if statement to avoid null pointer exception?
            // switch from double to BigDecimal
            double balance = jdbcTemplate.queryForObject(sql, Double.class, currentUserId);
            return balance;
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoExceptions("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoExceptions("SQL syntax error", e);
        } catch (NullPointerException e){
            throw new NullPointerException();
        }
    }

    @Transactional
    public void makeTransfer (int SendingUserId, int ReceivingUserId, double amountToSend){

        String pullMoneySql = "UPDATE account SET balance = ? WHERE user_id = ?;";

        String placeMoneySql = "UPDATE account SET balance = ? WHERE user_id = ?;";

        try {
            double sendingUserBalance = viewBalance(SendingUserId);
            double receivingUserBalance = viewBalance(ReceivingUserId);

            sendingUserBalance = sendingUserBalance - amountToSend;
            receivingUserBalance = receivingUserBalance + amountToSend;

            int numberOfRows = jdbcTemplate.update(pullMoneySql, sendingUserBalance, SendingUserId);
            int numberOfRows2 = jdbcTemplate.update(placeMoneySql, receivingUserBalance, ReceivingUserId);


        } catch (Exception e){
            e.getMessage();
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
