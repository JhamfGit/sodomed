package com.sodemed.exceptions;

public class TokenNotValid extends RuntimeException{

    public TokenNotValid(String message) {
        super(message);
    }
}