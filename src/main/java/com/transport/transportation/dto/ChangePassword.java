package com.transport.transportation.dto;

import java.io.Serializable;

public class ChangePassword implements Serializable {
    private String username;
    private String password;
    private String newpassword;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNewpassword() {
        return newpassword;
    }
}
