package com.example.agirafirstproject.controller;

import com.example.agirafirstproject.dto.CartRequestDto;
import com.example.agirafirstproject.dto.CartResponseDto;
import com.example.agirafirstproject.model.Cart;
import com.example.agirafirstproject.service.CartService;
import com.example.agirafirstproject.utility.CartMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private CartMapper cartMapper;
    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public CartResponseDto getCartById(@PathVariable UUID uuid){
        Cart cart = cartService.getCart(uuid, false);
        return cartMapper.cartToCartResponseDto(cart);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public CartResponseDto addCart(CartRequestDto cartRequestDto){
        Cart cart = cartService.addCart(cartRequestDto.getUserId(), cartRequestDto.getProductUUIDList());
        return cartMapper.cartToCartResponseDto(cart);
    }
}
