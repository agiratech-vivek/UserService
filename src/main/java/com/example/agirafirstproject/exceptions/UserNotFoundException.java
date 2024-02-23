package com.example.agirafirstproject.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message, long id) {
        super(String.format(message + "%d", id));
    }
}
