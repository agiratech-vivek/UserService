package com.example.agirafirstproject.service;

import com.example.agirafirstproject.model.Order;

import java.util.UUID;

public interface OrderService {
    Order getOrderById(UUID uuid, boolean deleted);
    Order addOrder(UUID userId, UUID cartId);
}
