package com.techelevator.tenmo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TenmoApplication {


    // if statement to avoid null pointer exception? - JdbcAccountDao.viewBalance()
    // Authorizations - JdbcAccountDao class
    // switch from double to BigDecimal - JdbcAccountDao.viewBalance()

    public static void main(String[] args) {
        SpringApplication.run(TenmoApplication.class, args);
    }

}
