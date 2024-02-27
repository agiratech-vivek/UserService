package com.example.agirafirstproject.dto;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * DTO for {@link com.example.agirafirstproject.model.Category}
 */
@Value
public class CategoryRequestDto implements Serializable {
    @NotNull(message = "Name cannot be null or empty")
    @NotEmpty(message = "Name cannot be null or empty")
    @NotBlank(message = "Name cannot be null or empty")
    String name;
}