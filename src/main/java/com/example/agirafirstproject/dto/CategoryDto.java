package com.example.agirafirstproject.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.agirafirstproject.model.Category}
 */
@Getter
@Setter
public class CategoryDto implements Serializable {
    String name;
}