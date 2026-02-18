package com.sodemed.exceptions;

public class InvalidSendEmailException extends RuntimeException {

    public InvalidSendEmailException(String message) {
        super(message);
    }

}
