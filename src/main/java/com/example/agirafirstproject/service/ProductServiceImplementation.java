package com.example.agirafirstproject.service;

import com.example.agirafirstproject.exceptions.ProductNotFoundException;
import com.example.agirafirstproject.model.Product;
import com.example.agirafirstproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImplementation implements ProductService{
    @Autowired
    ProductRepository productRepository;
    @Override
    public Product addProduct(Product product) {
        Optional<Product> productOptional = productRepository.findProductByTitleAndDeletedEquals(product.getTitle(), false);
        if(!productOptional.isPresent()){
            return productRepository.save(product);
        }
        Product oldProduct = productOptional.get();
        return updateProduct(oldProduct.getId(), product);
    }

    @Override
    public Product getProductByName(String name) {
        Optional<Product> productOptional = productRepository.findProductByTitleAndDeletedEquals(name, false);
        if(!productOptional.isPresent()){
            throw new ProductNotFoundException("name", name);
        }
        return productOptional.get();
    }

    @Override
    public Product getProductById(UUID id) {
        Optional<Product> productOptional = productRepository.findProductByIdAndDeletedEquals(id, false);
        if(!productOptional.isPresent()){
            throw new ProductNotFoundException("id", String.valueOf(id));
        }
        return productOptional.get();
    }

    @Override
    public Product updateProduct(UUID id, Product product) {
        Optional<Product> productOptional = productRepository.findProductByIdAndDeletedEquals(id, false);
        if(!productOptional.isPresent()){
            throw new ProductNotFoundException("id", String.valueOf(id));
        }
        Product oldProduct = productOptional.get();
        oldProduct.setUpdatedAt(LocalDateTime.now());
        oldProduct.setDeleted(false);
        oldProduct.setCategory(product.getCategory());
        oldProduct.setDescription(product.getDescription());
        oldProduct.setPrice(product.getPrice());
        return productRepository.save(oldProduct);
    }

    @Override
    public void deleteProduct(UUID uuid) {
        Optional<Product> productOptional = productRepository.findProductByIdAndDeletedEquals(uuid, false);
        if(!productOptional.isPresent()){
            throw new ProductNotFoundException("id", uuid.toString());
        }
        Product product = productOptional.get();
        product.setDeleted(true);
        productRepository.save(product);
    }
}
