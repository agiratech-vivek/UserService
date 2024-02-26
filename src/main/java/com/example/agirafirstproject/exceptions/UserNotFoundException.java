package com.example.agirafirstproject.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message, String id) {
        super(String.format(message + "%d", id));
    }
}
