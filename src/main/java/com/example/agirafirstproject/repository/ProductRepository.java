package com.example.agirafirstproject.repository;

import com.example.agirafirstproject.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findProductByTitleAndDeletedEquals(String name, boolean deleted);
    Optional<Product> findProductByIdAndDeletedEquals(UUID id, boolean deleted);
}
