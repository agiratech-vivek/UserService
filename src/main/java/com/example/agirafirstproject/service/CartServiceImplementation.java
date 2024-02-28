package com.example.agirafirstproject.service;

import com.example.agirafirstproject.exceptions.CartNotFoundException;
import com.example.agirafirstproject.model.Cart;
import com.example.agirafirstproject.model.Product;
import com.example.agirafirstproject.model.User;
import com.example.agirafirstproject.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CartServiceImplementation implements CartService{
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Override
    public Cart getCart(UUID uuid, boolean deleted) {
        Optional<Cart> optionalCart = cartRepository.findCartByIdAndDeletedEquals(uuid, deleted);
        if(!optionalCart.isPresent()){
            throw new CartNotFoundException("Cart not found with id: " + uuid);
        }
        return optionalCart.get();
    }

    @Override
    public Cart addCart(UUID userId, List<UUID> productUuidList) {
        User user = userService.getSingleUser(userId);
        List<Product> productList = productUuidList.stream()
                .map(uuid -> productService.getProductById(uuid))
                .collect(Collectors.toList());
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setProductList(productList);
        cart.setCreatedAt(LocalDateTime.now());
        cart.setUpdatedAt(LocalDateTime.now());
        return cartRepository.save(cart);
    }

    @Override
    public void deleteCart(UUID uuid) {
        Cart cart = getCart(uuid, false);
        cart.setDeleted(true);
        cart.setUpdatedAt(LocalDateTime.now());
        cartRepository.save(cart);
    }
}