package com.example.microservices.productservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequestDTO(String id, @NotEmpty(message = "Name is required") String name,
                                String description,
                                @NotNull(message = "Price is required")
                                @Positive(message = "Price must be a positive value")
                                BigDecimal price) {
}
