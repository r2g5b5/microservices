package com.example.microservices.inventoryservice.dto;


public record InventoryResponseDTO(Long Id,
                                   String skuCode,
                                   Integer quantity) {
}
