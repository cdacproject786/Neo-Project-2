package com.user.controller;

import com.user.pojo.UpdateToken;
import com.user.pojo.UserPojo;
import com.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userservice")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserPojo pojo)
    {
        try
        {
           UserPojo returnPojo =  service.saveUser(pojo);
           return new ResponseEntity<>(returnPojo, HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(null,HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @PostMapping("/token")
    public ResponseEntity<String> saveTaken(@RequestBody UpdateToken token)
    {
        try{
            service.setToken(token);
            return new ResponseEntity<>(null,HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null,HttpStatus.SERVICE_UNAVAILABLE);
        }

    }

    @GetMapping("/demo")
    public String demo()
    {
        return "Response from demo of user";
    }
}
