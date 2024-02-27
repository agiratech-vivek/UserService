package com.example.agirafirstproject.service;

import com.example.agirafirstproject.model.Product;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface ProductService {
    Product addProduct(Product product);

    Product getProductByName(String name);

    Product getProductById(UUID id);

    Product updateProduct(UUID id, Product product);

    void deleteProduct(UUID uuid);
}
