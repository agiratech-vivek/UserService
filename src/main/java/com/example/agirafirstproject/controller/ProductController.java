package com.example.agirafirstproject.controller;

import com.example.agirafirstproject.dto.ProductRequestDto;
import com.example.agirafirstproject.dto.ProductResponseDto;
import com.example.agirafirstproject.model.Category;
import com.example.agirafirstproject.model.Product;
import com.example.agirafirstproject.service.ProductService;
import com.example.agirafirstproject.utility.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    ProductMapper productMapper;
    @GetMapping("/name/{productName}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponseDto getProductByName(@PathVariable String productName){
        Product productByName = productService.getProductByName(productName);
        return productMapper.productToProductResponseDto(productByName);
    }

    @GetMapping("/id")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponseDto getProductById(@RequestParam UUID uuid){
        Product productById = productService.getProductById(uuid);
        return productMapper.productToProductResponseDto(productById);
    }

    @PostMapping
    public ProductResponseDto addProduct(@RequestBody ProductRequestDto productRequestDto){
        Product product = new Product();
        product.setPrice(productRequestDto.getPrice());
        product.setDescription(productRequestDto.getDescription());
        product.setTitle(productRequestDto.getTitle());
        Category category = new Category();
        category.setName(productRequestDto.getCategory().getName());
        product.setCategory(category);
        Product addedProduct = productService.addProduct(product);
        return productMapper.productToProductResponseDto(addedProduct);
    }
//
//    @PutMapping
//    @ResponseStatus(HttpStatus.OK)
//    public ProductResponseDto updateProduct(@RequestBody ProductRequestDto productRequestDto, UUID id){
//        Product product = modelMapper.map(productRequestDto, Product.class);
//        return modelMapper.map(productService.updateProduct(id, product), ProductResponseDto.class);
//    }

    @DeleteMapping("/{uuid}")
    public void deleteProduct(@PathVariable UUID uuid){
        productService.deleteProduct(uuid);
    }
}
