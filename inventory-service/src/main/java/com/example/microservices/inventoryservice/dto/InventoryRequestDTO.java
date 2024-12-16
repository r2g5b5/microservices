package com.example.microservices.inventoryservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public record InventoryRequestDTO(
        @NotNull(message = "skuCode cannot be null")
        @NotEmpty(message = "skuCode cannot be empty")
        String skuCode,
        @NotNull(message = "quantity cannot be null")
        @Positive(message = "quantity must be positive")
        Integer quantity) {
}
