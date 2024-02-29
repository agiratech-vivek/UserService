package com.example.agirafirstproject.service;

import com.example.agirafirstproject.exceptions.ProductNotFoundException;
import com.example.agirafirstproject.model.Category;
import com.example.agirafirstproject.model.Product;
import com.example.agirafirstproject.repository.CategoryRepository;
import com.example.agirafirstproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.data.repository.util.ClassUtils.ifPresent;

@Service
public class ProductServiceImplementation implements ProductService{
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public Product addProduct(Product product) {
        Optional<Category> optionalCategory = categoryRepository.findByNameAndAndDeletedEquals(product.getCategory().getName(), false);
                optionalCategory.ifPresent(product::setCategory);
        return productRepository.save(product );
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

    @Transactional
    public void addBulkProduct(List<Product> productList){
        productList.forEach(product -> {
            Optional<Category> optionalCategory =
                    categoryRepository.findByNameAndAndDeletedEquals(product.getCategory().getName(), false);
            optionalCategory.ifPresent(product::setCategory);
            productRepository.save(product);
        });
    }
}
