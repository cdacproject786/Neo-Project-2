package com.user.pojo;

public class UpdateToken {

    private String email;
    private String token;

    public UpdateToken(String email, String token) {
        this.email = email;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }
}
