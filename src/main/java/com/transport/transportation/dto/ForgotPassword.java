package com.transport.transportation.dto;

import javax.validation.constraints.NotNull;

public class ForgotPassword {

    @NotNull
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
