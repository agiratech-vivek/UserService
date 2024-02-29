package com.example.agirafirstproject.exceptions;

public class NoResultsFoundException extends Throwable {
    public NoResultsFoundException(String noResultsFound) {
        super(noResultsFound);
    }
}
