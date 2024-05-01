package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.RowSet;
import java.security.Principal;
@Component
public class JdbcAccountDao implements AccountDao{


    public JdbcAccountDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    private JdbcTemplate jdbcTemplate;


    public double viewBalance(int currentUserId){
        String sql = "SELECT balance FROM account WHERE user_id = ?";
        double balance = jdbcTemplate.queryForObject(sql, Double.class, currentUserId);
        return balance;
    }

    public Account mapAccount(SqlRowSet rs){
        Account account = new Account();
        account.setAccountId(rs.getInt("account_id"));
        account.setUserId(rs.getInt("user_id"));
        account.setBalance(rs.getDouble("balance"));
            return account;
    }

}
