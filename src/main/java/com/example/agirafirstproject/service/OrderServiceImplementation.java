package com.example.agirafirstproject.service;

import com.example.agirafirstproject.exceptions.OrderNotFoundException;
import com.example.agirafirstproject.model.Cart;
import com.example.agirafirstproject.model.Order;
import com.example.agirafirstproject.model.Product;
import com.example.agirafirstproject.model.User;
import com.example.agirafirstproject.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
@Service
public class OrderServiceImplementation implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;
    @Override
    public Order getOrderById(UUID uuid, boolean deleted) {
        Optional<Order> orderOptional = orderRepository.findOrderByIdAndDeletedEquals(uuid, false);
        if(!orderOptional.isPresent()){
            throw new OrderNotFoundException("Order not found with id: " + uuid);
        }
        return orderOptional.get();
    }

    @Override
    public Order addOrder(UUID userId, UUID cartId) {
        User user = userService.getSingleUser(userId);
        Cart cart = cartService.getCart(cartId, false);
        Order order = new Order();
        order.setUser(user);
        order.setCart(cart);
        double totalPrice = order.getCart().getProductList()
                .stream()
                .mapToDouble(Product::getPrice)
                .reduce(0, Double::sum);
        order.setTotalPrice(totalPrice);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }
}