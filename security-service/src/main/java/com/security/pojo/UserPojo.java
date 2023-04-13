package com.security.pojo;

public class UserPojo {

    private String email;
    private String password;
    private String phoneNo;

    private String jwtToken;

    public UserPojo(String email, String password, String phoneNo, String jwtToken) {
        this.email = email;
        this.password = password;
        this.phoneNo = phoneNo;
        this.jwtToken = jwtToken;
    }

    public UserPojo() {}

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
}
