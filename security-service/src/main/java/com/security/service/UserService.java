package com.security.service;

import com.security.entity.User;
import com.security.pojo.UserFeignPojo;
import com.security.pojo.UserPojo;
import com.security.repository.SaveTokeRepo;
import com.security.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private SaveTokeRepo tokenRepo;

    public UserFeignPojo saveUser(UserPojo pojo)
    {
        User user = mapper.map(pojo, User.class);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("USER");
        User savedUser =  userRepository.save(user);
        return mapper.map(savedUser,UserFeignPojo.class);
    }

    public UserPojo getUser(String email)
    {
       Optional<User> foundUser = userRepository.findById(email);
       return mapper.map(foundUser,UserPojo.class);
    }

    public boolean authenticateUser(UserPojo Dbpojo,UserPojo requestPojo)
    {
       return encoder.matches(requestPojo.getPassword(),Dbpojo.getPassword());
    }

    public void saveToken(String token,String email)
    {
        tokenRepo.insertToken(token,email);
    }

    public UserPojo getUserByMail(String email)
    {
       User user = userRepository.findByEmail(email);
       return mapper.map(user,UserPojo.class);
    }
}
