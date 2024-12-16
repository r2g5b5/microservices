package com.example.microservices.orderservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record OrderRequestDTO(
        String orderNumber,
        @NotNull(message = "skuCode cannot be null")
        @NotEmpty(message = "skuCode cannot be empty")
        String skuCode,

        @NotNull(message = "price cannot be null")
        @Positive(message = "price must be positive")
        BigDecimal price,

        @NotNull(message = "quantity cannot be null")
        @Positive(message = "quantity must be positive")
        Integer quantity) {
}

