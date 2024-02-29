package com.example.agirafirstproject.controller;

import com.example.agirafirstproject.dto.ProductRequestDto;
import com.example.agirafirstproject.dto.ProductResponseDto;
import com.example.agirafirstproject.model.Product;
import com.example.agirafirstproject.service.ProductService;
import com.example.agirafirstproject.utility.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @PostMapping("/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    public void addBulkProduct(@RequestBody List<ProductRequestDto> productRequestDtoList){
        List<Product> products = productRequestDtoList.stream()
                .map(productRequestDto ->
                        productMapper.productRequestDtoToProduct(productRequestDto)
                ).collect(Collectors.toList());
        productService.addBulkProduct(products);
    }

    @PostMapping
    public ProductResponseDto addProduct(@RequestBody ProductRequestDto productRequestDto){
        Product product = productService.addProduct(productMapper.productRequestDtoToProduct(productRequestDto));
        return productMapper.productToProductResponseDto(product);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDto updateProduct(@RequestBody ProductRequestDto productRequestDto, UUID id){
        Product product = productService.updateProduct(id, productMapper.productRequestDtoToProduct(productRequestDto));
        return productMapper.productToProductResponseDto(product);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable UUID uuid){
        productService.deleteProduct(uuid);
    }
}
