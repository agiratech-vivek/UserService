package com.example.agirafirstproject.exceptions;

public class UserAlreadyRegisteredException extends RuntimeException{
    public UserAlreadyRegisteredException(String message) {
        super(message);
    }
}
