package com.example.microservices.orderservice.mapper;

import com.example.microservices.orderservice.dto.OrderRequestDTO;
import com.example.microservices.orderservice.dto.OrderResponseDTO;
import com.example.microservices.orderservice.entity.Order;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderMapper {

    public Order toEntity(OrderRequestDTO requestDTO) {
        return Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .skuCode(requestDTO.skuCode())
                .price(requestDTO.price())
                .quantity(requestDTO.quantity())
                .build();
    }

    public OrderResponseDTO toDTO(Order order) {
        return new OrderResponseDTO(
                order.getId(),
                order.getOrderNumber(),
                order.getSkuCode(),
                order.getPrice(),
                order.getQuantity()
        );
    }

}
