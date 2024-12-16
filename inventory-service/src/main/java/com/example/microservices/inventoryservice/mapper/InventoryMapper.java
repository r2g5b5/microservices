package com.example.microservices.inventoryservice.mapper;

import com.example.microservices.inventoryservice.dto.InventoryRequestDTO;
import com.example.microservices.inventoryservice.dto.InventoryResponseDTO;
import com.example.microservices.inventoryservice.entity.Inventory;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapper {

    public Inventory toEntity(InventoryRequestDTO requestDTO) {
        return Inventory.builder()
                .skuCode(requestDTO.skuCode())
                .quantity(requestDTO.quantity())
                .build();
    }

    public InventoryResponseDTO toDTO(Inventory inventory) {
        return new InventoryResponseDTO(
                inventory.getId(),
                inventory.getSkuCode(),
                inventory.getQuantity()
        );
    }

}
