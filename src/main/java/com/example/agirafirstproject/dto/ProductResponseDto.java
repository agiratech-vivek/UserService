package com.example.agirafirstproject.dto;

import com.example.agirafirstproject.dto.CategoryDto;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.agirafirstproject.model.Product}
 */
@Getter
@Setter
public class ProductResponseDto implements Serializable {
    String name;
    String category;
    double price;
    String description;
}