package com.security.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    private String email;
    private String password;
    private String phoneNo;
    private String jwtToken;
    private String role;

    public User(String email, String password, String phoneNo, String jwtToken, String role) {
        this.email = email;
        this.password = password;
        this.phoneNo = phoneNo;
        this.jwtToken = jwtToken;
        this.role = role;
    }

    public User(){}

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

}
