package com.example.microservices.inventoryservice.service;

import com.example.microservices.inventoryservice.dto.InventoryRequestDTO;
import com.example.microservices.inventoryservice.dto.InventoryResponseDTO;
import com.example.microservices.inventoryservice.entity.Inventory;
import com.example.microservices.inventoryservice.mapper.InventoryMapper;
import com.example.microservices.inventoryservice.repository.InventoryRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepo repo;
    private final InventoryMapper mapper;

    @Transactional(rollbackFor = Exception.class)
    public void save(InventoryRequestDTO requestDTO) {
        Inventory inventory = mapper.toEntity(requestDTO);
        repo.save(inventory);
        log.info("Inventory saved: {}", inventory);
    }

    @Transactional(readOnly = true)
    public boolean isInStock(String skuCode, Integer quantity) {
        return repo.existsBySkuCodeAndQuantityGreaterThanEqual(skuCode, quantity);
    }

    @Transactional(readOnly = true)
    public List<InventoryResponseDTO> findAll() {
        List<Inventory> inventorys = repo.findAll();
        return inventorys.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}
