package com.example.agirafirstproject.dto;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

/**
 * DTO for {@link com.example.agirafirstproject.model.Product}
 */
@Value
public class ProductRequestDto implements Serializable {
    @NotNull(message = "Title cannot be empty or null")
    @NotBlank(message = "Title cannot be empty or null")
    String title;
    @NotNull(message = "Category cannot be empty or null")
    CategoryRequestDto category;
    @Positive(message = "Price not valid")
    double price;
    @NotNull(message = "Description not valid")
    @NotEmpty(message = "Description not valid")
    @NotBlank(message = " Description not valid")
    String description;
}