package com.example.microservices.orderservice.controller;

import com.example.microservices.orderservice.dto.OrderRequestDTO;
import com.example.microservices.orderservice.dto.OrderResponseDTO;
import com.example.microservices.orderservice.exception.InvalidInputException;
import com.example.microservices.orderservice.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Validated
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Void> placeOrder(@RequestBody @Valid OrderRequestDTO request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidInputException(bindingResult.getFieldErrors());
        }
        orderService.placeOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllProducts() {
        List<OrderResponseDTO> products = orderService.getAllOrders();
        return ResponseEntity.ok(products);
    }

}
