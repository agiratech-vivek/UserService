package com.example.agirafirstproject.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CartRequestDto {
    private UUID userId;
    private List<UUID> productUUIDList;
}
