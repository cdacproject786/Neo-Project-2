package com.gateway.controller;

import com.gateway.pojo.UserRegister;
import com.gateway.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
public class RegisterController {

    @Autowired
    UserRegisterService service;
    @PostMapping("/user")
    public ResponseEntity<?> registerUser(@RequestBody UserRegister pojo)
    {
        String response  = service.registerUser(pojo);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }


}
