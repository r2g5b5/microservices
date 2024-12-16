package com.example.microservices.inventoryservice.controller;

import com.example.microservices.inventoryservice.dto.InventoryRequestDTO;
import com.example.microservices.inventoryservice.dto.InventoryResponseDTO;
import com.example.microservices.inventoryservice.exception.InvalidInputException;
import com.example.microservices.inventoryservice.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Validated
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid InventoryRequestDTO request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidInputException(bindingResult.getFieldErrors());
        }
        inventoryService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<InventoryResponseDTO>> findAll() {
        List<InventoryResponseDTO> products = inventoryService.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping(value = "/isInStock")
    public ResponseEntity<Boolean> isInStock(@RequestParam String skuCode,@RequestParam Integer quantity) {
        boolean isInStock = inventoryService.isInStock(skuCode,quantity);
        return ResponseEntity.ok(isInStock);
    }

}
