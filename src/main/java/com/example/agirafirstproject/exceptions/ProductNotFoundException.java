package com.example.agirafirstproject.exceptions;

import java.util.UUID;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String datatype, String param) {
        super("Product not found with " + datatype + ": " + param);
    }
}
