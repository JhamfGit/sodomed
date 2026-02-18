package com.sodemed.exceptions;

public class UserLoginNotValid extends RuntimeException {

    public UserLoginNotValid(String message) {
        super(message);
    }
}