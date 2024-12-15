package com.example.microservices.productservice.mapper;

import com.example.microservices.productservice.dto.ProductRequestDTO;
import com.example.microservices.productservice.dto.ProductResponseDTO;
import com.example.microservices.productservice.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(ProductRequestDTO request) {
        return Product.builder()
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .build();
    }

    public ProductResponseDTO toDTO(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }

}
