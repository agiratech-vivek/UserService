package com.example.agirafirstproject.controller;

import com.example.agirafirstproject.dto.*;
import com.example.agirafirstproject.model.Cart;
import com.example.agirafirstproject.model.Category;
import com.example.agirafirstproject.model.Order;
import com.example.agirafirstproject.service.OrderService;
import com.example.agirafirstproject.utility.UserMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    public OrderResponseDto getOrderById(@PathVariable UUID uuid) {
        Order order = orderService.getOrderById(uuid, false);
        Cart cart = order.getCart();
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        CartResponseDto cartResponseDto = new CartResponseDto();
        List<ProductResponseDto> productResponseDtoList = cart.getProductList()
                .stream()
                .map(product -> {
                    ProductResponseDto productResponseDto = new ProductResponseDto();
                    Category category = product.getCategory();
                    CategoryDto categoryDto = new CategoryDto();
                    categoryDto.setName(category.getName());
                    productResponseDto.setCategory(categoryDto);
                    productResponseDto.setName(product.getTitle());
                    productResponseDto.setDescription(product.getDescription());
                    productResponseDto.setPrice(product.getPrice());
                    return productResponseDto;
                }).collect(Collectors.toList());
        cartResponseDto.setUserResponseDto(userMapper.userToUserResponseDto(cart.getUser()));
        cartResponseDto.setProducts(productResponseDtoList);
        orderResponseDto.setOrderPlacedAt(order.getCreatedAt());
        orderResponseDto.setCartDetails(cartResponseDto);
        return orderResponseDto;
    }
}
