package com.gateway.pojo;

public class UserRegister {

    private String email;
    private String password;
    private String phoneNo;
    private String role;
    private String jwtToken;


    public UserRegister(String email, String password, String phoneNo, String role, String jwtToken) {
        this.email = email;
        this.password = password;
        this.phoneNo = phoneNo;
        this.role = role;
        this.jwtToken = jwtToken;
    }

    public UserRegister() {}

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
