package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferDto;
import com.techelevator.tenmo.model.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

public class AccountService {
    private String URL;
    public AccountService(String URL){
        this.URL = URL;
    }
    RestTemplate restTemplate = new RestTemplate();
    public double viewBalance(AuthenticatedUser currentUser){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(currentUser.getToken());
        HttpEntity<Void> entity = new HttpEntity<>(headers);


        ResponseEntity<Double> response = restTemplate.exchange(
                URL + "/balance", HttpMethod.GET, entity, Double.class);
        double balance = response.getBody();
        return balance;
    }
    public List<User> viewUsers(AuthenticatedUser currentUser){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(currentUser.getToken());
        HttpEntity<Void> entity = new HttpEntity<>(headers);


        ResponseEntity<User[]> response = restTemplate.exchange(
                URL + "/users", HttpMethod.GET, entity, User[].class);

        User[] userArray = response.getBody();
        return Arrays.asList(userArray);
    }
    public void transferBucks(AuthenticatedUser currentUser, int receivingUserId, BigDecimal amountToSend){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(currentUser.getToken());

        TransferDto transferDto = new TransferDto();
        transferDto.setAmount(amountToSend);
        transferDto.setReceiverId(receivingUserId);
        transferDto.setSenderId(currentUser.getUser().getId());

        HttpEntity<TransferDto> entity = new HttpEntity<>(transferDto, headers);

        restTemplate.exchange(
                URL + "/transfer", HttpMethod.POST, entity, Void.class);

        System.out.println("Transfer successful! Your current balance is: $" + viewBalance(currentUser));

    }

    public List <Transfer> transferHistory(AuthenticatedUser currentUser) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(currentUser.getToken());

        HttpEntity<Transfer> entity = new HttpEntity<>(headers);

        ResponseEntity<Transfer[]> response = restTemplate.exchange(URL + "/seetransfers", HttpMethod.GET, entity, Transfer[].class);

        Transfer[] transactionHistory = response.getBody();

        return Arrays.asList(transactionHistory);
    }



}
