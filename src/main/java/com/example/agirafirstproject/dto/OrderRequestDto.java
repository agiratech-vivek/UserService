package com.example.agirafirstproject.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class OrderRequestDto {
    private UUID userId;
    private UUID cartId;
}
