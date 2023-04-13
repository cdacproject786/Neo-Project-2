package com.security.service;

import com.security.IFeign.UserFeign;
import com.security.pojo.UserFeignPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeignUserService {

    @Autowired
    UserFeign userFeign;

    public void saveUserRemote(UserFeignPojo pojo)
    {
        userFeign.registerUser(pojo);
    }
}
