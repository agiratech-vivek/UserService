package com.example.agirafirstproject.utility;

import com.example.agirafirstproject.dto.CategoryDto;
import com.example.agirafirstproject.dto.ProductRequestDto;
import com.example.agirafirstproject.dto.ProductResponseDto;
import com.example.agirafirstproject.model.Category;
import com.example.agirafirstproject.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductResponseDto productToProductResponseDto(Product product){
        ProductResponseDto productResponseDto = new ProductResponseDto();
        Category category = product.getCategory();
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(category.getName());
        productResponseDto.setCategory(categoryDto.getName());
        productResponseDto.setName(product.getTitle());
        productResponseDto.setDescription(product.getDescription());
        productResponseDto.setPrice(product.getPrice());
        return productResponseDto;
    }

    public Product productRequestDtoToProduct(ProductRequestDto productRequestDto){
        Product product = new Product();
        product.setPrice(productRequestDto.getPrice());
        product.setDescription(productRequestDto.getDescription());
        product.setTitle(productRequestDto.getTitle());
        Category category = new Category();
        category.setName(productRequestDto.getCategory().getName());
        product.setCategory(category);
        return product;
    }
}
