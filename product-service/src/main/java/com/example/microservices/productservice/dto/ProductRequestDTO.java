package com.example.microservices.productservice.dto;

import java.math.BigDecimal;

public record ProductRequestDTO(String id, String name, String description, BigDecimal price) {
}
