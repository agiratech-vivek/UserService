package com.example.agirafirstproject.dto;

import com.example.agirafirstproject.model.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderResponseDto {
    private CartResponseDto cartDetails;
    private LocalDateTime orderPlacedAt;
    private double totalPrice;
}
