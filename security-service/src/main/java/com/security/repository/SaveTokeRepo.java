package com.security.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class SaveTokeRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void insertToken(String token,String email)
    {
        entityManager.createNativeQuery("update user u set u.jwt_token = ? where u.email = ?")
                .setParameter(1,token)
                .setParameter(2,email)
                .executeUpdate();
    }
}
