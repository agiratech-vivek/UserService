package com.example.agirafirstproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto{
    String title;
    CategoryRequestDto category;
    double price;
    String description;
}