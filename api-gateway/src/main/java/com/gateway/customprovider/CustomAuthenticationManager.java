package com.gateway.customprovider;

import com.gateway.exception.AuthenticationFailedException;
import com.gateway.pojo.UserLogin;
import com.gateway.pojo.UserRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    @Autowired
    private RestTemplate template;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();
       // System.out.println("Entered the authenticate method");
        UserLogin user = new UserLogin(email,password);
        //System.out.println("Before rest call to security service");
        ResponseEntity<UserRegister> pojo =  template.postForEntity("http://localhost:8020/user/security/login",user, UserRegister.class);
        //System.out.println("After rest call to security service");
        UserRegister registeredUser = pojo.getBody();
        if(pojo.getStatusCode() == HttpStatus.UNAUTHORIZED)
            throw new AuthenticationFailedException("User is invalid");
        else
        {
            return new UsernamePasswordAuthenticationToken(registeredUser.getEmail(),registeredUser.getJwtToken(),null);
           // return new CustomAuthentication(registeredUser.getEmail(),registeredUser.getJwtToken(),true);
        }
    }
}
