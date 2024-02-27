package com.example.agirafirstproject.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartResponseDto {
    private UserResponseDto userResponseDto;
    private List<ProductResponseDto> products;
}
