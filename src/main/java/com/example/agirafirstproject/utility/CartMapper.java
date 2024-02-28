package com.example.agirafirstproject.utility;

import com.example.agirafirstproject.dto.CartResponseDto;
import com.example.agirafirstproject.dto.ProductResponseDto;
import com.example.agirafirstproject.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {
    @Autowired
    UserMapper userMapper;
    @Autowired
    ProductMapper productMapper;
    public CartResponseDto cartToCartResponseDto(Cart cart){
        CartResponseDto cartResponseDto = new CartResponseDto();
        cartResponseDto.setUserResponseDto(userMapper.userToUserResponseDto(cart.getUser()));
        List<ProductResponseDto> productResponseDtoList = cart.getProductList()
                .stream()
                .map(product -> productMapper.productToProductResponseDto(product))
                .collect(Collectors.toList());
        cartResponseDto.setProducts(productResponseDtoList);
        return cartResponseDto;
    }
}
