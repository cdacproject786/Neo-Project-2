package com.gateway.service;

import com.gateway.pojo.UpdateToken;
import com.gateway.pojo.UserRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserLoginService {

    @Autowired
    RestTemplate template;

    public void saveToken(String token,String email)
    {
        UpdateToken updateToken = new UpdateToken(email,token);
       template.postForEntity("http://localhost:8030/user/token",updateToken,null);
    }

    public UserRegister getUser(String email)
    {
       return template.getForObject("http://localhost:8030/user/security/gettoken/"+email,UserRegister.class);

    }
}
