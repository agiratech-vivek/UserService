package com.example.agirafirstproject.service;

import com.example.agirafirstproject.exceptions.OrderNotFoundException;
import com.example.agirafirstproject.model.Order;
import com.example.agirafirstproject.model.Product;
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
    @Override
    public Order getOrderById(UUID uuid, boolean deleted) {
        Optional<Order> orderOptional = orderRepository.findOrderByIdAndDeletedEquals(uuid, false);
        if(!orderOptional.isPresent()){
            throw new OrderNotFoundException("Order not found with id: " + uuid);
        }
        return orderOptional.get();
    }

    @Override
    public Order addOrder(Order order) {
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