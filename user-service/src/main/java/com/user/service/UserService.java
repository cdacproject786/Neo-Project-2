package com.user.service;

import com.user.entity.User;
import com.user.pojo.UpdateToken;
import com.user.pojo.UserPojo;
import com.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    public UserPojo saveUser(UserPojo pojo)
    {
        User user = modelMapper.map(pojo,User.class);
        //saving the user object
        User savedUser = userRepository.save(user);
        return  modelMapper.map(savedUser,UserPojo.class);
    }

    public void setToken(UpdateToken token)
    {
        userRepository.updateUserToken(token.getToken(),token.getEmail());
    }
}
