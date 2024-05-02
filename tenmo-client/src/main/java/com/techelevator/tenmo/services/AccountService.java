package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

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
                URL, HttpMethod.GET, entity, Double.class);
        double balance = response.getBody();
        return balance;
    }
}
