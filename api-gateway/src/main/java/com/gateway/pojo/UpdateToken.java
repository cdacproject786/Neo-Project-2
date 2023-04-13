package com.gateway.pojo;

public class UpdateToken {

    private String email;
    private String token;

    public UpdateToken(String email, String token) {
        this.email = email;
        this.token = token;
    }
}
