package com.user.repository;

import com.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {


    @Query(nativeQuery = true, value = "update  u set u.jwt_token = 1 where u.email = 2")
    public void updateUserToken(String token, String email);
}
