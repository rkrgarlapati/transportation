package com.transport.transportation.dto;

import java.io.Serializable;

public class LoginUser implements Serializable {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
