package com.security.controller;


import com.security.jwt.Jwtutil;
import com.security.pojo.RegisterResponse;
import com.security.pojo.UserFeignPojo;
import com.security.pojo.UserPojo;
import com.security.service.FeignUserService;
import com.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/security")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private Jwtutil jwtutil;

    @Autowired
    private FeignUserService feignUserService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserPojo pojo)
    {
      try{
          UserFeignPojo savedUser = service.saveUser(pojo);
          feignUserService.saveUserRemote(savedUser);
          return new ResponseEntity<>(new RegisterResponse("User registered successfully"), HttpStatus.OK);
      }
      catch(Exception e)
      {
          e.printStackTrace();
          return new ResponseEntity<>(new RegisterResponse("Service Unavailable Try again later"),HttpStatus.SERVICE_UNAVAILABLE);
      }
    }

    @PostMapping("/login")
    public ResponseEntity<?> getUser(@RequestBody UserPojo pojo)
    {
        try{
            UserPojo foundUser = service.getUser(pojo.getEmail());
            boolean authenticated = service.authenticateUser(foundUser,pojo);
            if(authenticated)
            {
                //generate access token
                String token = jwtutil.generateAccessToken(foundUser);
               // System.out.println(token);
                //code to save token into the database
                service.saveToken(token,foundUser.getEmail());
                foundUser.setJwtToken(token);
                return new ResponseEntity<>(foundUser,HttpStatus.OK);
            }

            else
                return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/gettoken/{email}")
    public UserPojo demo(@PathVariable("email")String email)
    {
        try {
           return service.getUserByMail(email);
        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
        return null;
    }

    @GetMapping("/demo")
    public String demo()
    {
        return "response from demo";
    }
}
