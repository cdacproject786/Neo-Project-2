package com.security.IFeign;

import com.security.pojo.UserFeignPojo;
import com.security.pojo.UserPojo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "USER-SERVICE")
public interface UserFeign {

    @PostMapping("/userservice/register")
    public ResponseEntity<?> registerUser(@RequestBody UserFeignPojo pojo);
}
