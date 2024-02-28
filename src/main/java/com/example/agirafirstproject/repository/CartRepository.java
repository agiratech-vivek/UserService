package com.example.agirafirstproject.repository;

import com.example.agirafirstproject.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {
    Optional<Cart> findCartByIdAndDeletedEquals(UUID uuid, boolean deleted);
}
