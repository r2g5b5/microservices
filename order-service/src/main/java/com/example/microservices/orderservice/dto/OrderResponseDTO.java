package com.example.microservices.orderservice.dto;

import java.math.BigDecimal;

public record OrderResponseDTO(Long Id,
                               String orderNumber,
                               String skuCode,
                               BigDecimal price,
                               Integer quantity) {
}
