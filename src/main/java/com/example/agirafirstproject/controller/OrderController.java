package com.example.agirafirstproject.controller;

import com.example.agirafirstproject.dto.*;
import com.example.agirafirstproject.model.Cart;
import com.example.agirafirstproject.model.Category;
import com.example.agirafirstproject.model.Order;
import com.example.agirafirstproject.service.OrderService;
import com.example.agirafirstproject.utility.ProductMapper;
import com.example.agirafirstproject.utility.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProductMapper productMapper;

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponseDto getOrderById(@PathVariable UUID uuid) {
        Order order = orderService.getOrderById(uuid, false);
        return getOrderResponseDto(order);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto){
        Order order = orderService.addOrder(orderRequestDto.getUserId(), orderRequestDto.getCartId());
        return getOrderResponseDto(order);
    }

    private OrderResponseDto getOrderResponseDto(Order order) {
        Cart cart = order.getCart();
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        CartResponseDto cartResponseDto = new CartResponseDto();
        List<ProductResponseDto> productResponseDtoList = cart.getProductList()
                .stream()
                .map(product ->
                    productMapper.productToProductResponseDto(product)
                ).collect(Collectors.toList());
        cartResponseDto.setUserResponseDto(userMapper.userToUserResponseDto(cart.getUser()));
        cartResponseDto.setProducts(productResponseDtoList);
        orderResponseDto.setOrderPlacedAt(order.getCreatedAt());
        orderResponseDto.setCartDetails(cartResponseDto);
        return orderResponseDto;
    }
}
