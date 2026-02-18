package com.sodemed.exceptions;

public class InvalidTakeException extends RuntimeException {
    public InvalidTakeException(String message) {
        super(message);
    }
}
