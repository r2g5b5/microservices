package com.example.microservices.inventoryservice.repository;

import com.example.microservices.inventoryservice.entity.Inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Integer> {
    boolean existsBySkuCodeAndQuantityGreaterThanEqual( String skuCode,  Integer quantity);
}
