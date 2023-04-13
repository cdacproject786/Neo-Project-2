package com.gateway.controller;

import com.gateway.pojo.UserLogin;
import com.gateway.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/user")
    public ResponseEntity<?> loginUser(@RequestBody UserLogin userLogin)
    {
        try
        {
           System.out.println("Entered the api-gateway login controller");
           Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getEmail(),userLogin.getPassword()));
           if(authentication.isAuthenticated())
           {
               //System.out.println("Authentication successful from authenticate method");
               return new ResponseEntity<>(authentication, HttpStatus.ACCEPTED);
           }
           else
           {
               System.out.println("Authentication is not successful from authenticate method");
               return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
           }

        }
        catch (Exception e)
        {
              e.printStackTrace();
              return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
