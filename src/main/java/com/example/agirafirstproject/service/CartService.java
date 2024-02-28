package com.example.agirafirstproject.service;

import com.example.agirafirstproject.model.Cart;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface CartService {
    Cart getCart(UUID uuid, boolean deleted);
    Cart addCart(UUID userId, List<UUID> productUuidList);
    void deleteCart(UUID uuid);
}
