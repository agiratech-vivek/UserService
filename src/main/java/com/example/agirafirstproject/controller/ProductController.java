package com.example.agirafirstproject.controller;

import com.example.agirafirstproject.dto.ProductRequestDto;
import com.example.agirafirstproject.dto.ProductResponseDto;
import com.example.agirafirstproject.model.Product;
import com.example.agirafirstproject.service.ProductService;
import org.modelmapper.ModelMapper;
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
    ModelMapper modelMapper;
    @GetMapping("/name/{productName}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponseDto getProductByName(@PathVariable String productName){
        Product productByName = productService.getProductByName(productName);
        return modelMapper.map(productByName, ProductResponseDto.class);
    }

    @GetMapping("/id")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponseDto getProductById(@RequestParam UUID uuid){
        Product productById = productService.getProductById(uuid);
        return modelMapper.map(productById, ProductResponseDto.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ProductResponseDto addProduct(@RequestBody ProductRequestDto productRequestDto){
        Product mappedProduct = modelMapper.map(productRequestDto, Product.class);
        return modelMapper.map(productService.addProduct(mappedProduct), ProductResponseDto.class);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ProductResponseDto updateProduct(@RequestBody ProductRequestDto productRequestDto, UUID id){
        Product product = modelMapper.map(productRequestDto, Product.class);
        return modelMapper.map(productService.updateProduct(id, product), ProductResponseDto.class);
    }

    @DeleteMapping("/{uuid}")
    public void deleteProduct(@PathVariable UUID uuid){
        productService.deleteProduct(uuid);
    }
}
