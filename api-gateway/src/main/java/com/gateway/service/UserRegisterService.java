package com.gateway.service;

import com.gateway.pojo.UserRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserRegisterService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    PasswordEncoder encoder;
    public String registerUser(UserRegister user)
    {
       user.setPassword(encoder.encode(user.getPassword()));
       ResponseEntity<String> registerResponse = restTemplate.postForEntity("http://localhost:8030/api/security/register",user, String.class);
       String response = registerResponse.getBody();
       return response;
    }

}
