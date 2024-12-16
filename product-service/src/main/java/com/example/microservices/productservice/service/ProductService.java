package com.example.microservices.productservice.service;

import com.example.microservices.productservice.dto.ProductRequestDTO;
import com.example.microservices.productservice.dto.ProductResponseDTO;
import com.example.microservices.productservice.entity.Product;
import com.example.microservices.productservice.mapper.ProductMapper;
import com.example.microservices.productservice.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepo productRepo;
    private final ProductMapper productMapper;

    @Transactional(rollbackFor = Exception.class)
    public void createProduct(ProductRequestDTO request) {
        Product product = productMapper.toEntity(request);
        productRepo.save(product);
        log.info("Created product: {}", product);
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = productRepo.findAll();
        return products.stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }
}
