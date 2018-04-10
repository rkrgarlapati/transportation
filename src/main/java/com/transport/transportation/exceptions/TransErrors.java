package com.transport.transportation.exceptions;

public class TransErrors extends Exception{

    String error;
    public TransErrors(String error) {
        this.error = error;
    }
}
