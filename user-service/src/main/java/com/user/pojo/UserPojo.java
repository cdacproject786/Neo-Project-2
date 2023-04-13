package com.user.pojo;

public class UserPojo {

    private String email;
    private String password;
    private String phoneNo;

    public UserPojo(String email, String password, String phoneNo) {
        this.email = email;
        this.password = password;
        this.phoneNo = phoneNo;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
