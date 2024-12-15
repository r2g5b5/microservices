package com.example.microservices.productservice.controller;

import com.example.microservices.productservice.dto.ProductRequestDTO;
import com.example.microservices.productservice.dto.ProductResponseDTO;
import com.example.microservices.productservice.exception.InvalidInputException;
import com.example.microservices.productservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody @Valid ProductRequestDTO request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidInputException(bindingResult.getFieldErrors());
        }
        productService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductResponseDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

}
