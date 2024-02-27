package com.example.agirafirstproject.repository;

import com.example.agirafirstproject.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    Optional<Order> findOrderByIdAndDeletedEquals(UUID uuid, boolean deleted);
}
