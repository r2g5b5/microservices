package com.example.microservices.orderservice.service;

import com.example.microservices.orderservice.client.InventoryClient;
import com.example.microservices.orderservice.dto.OrderRequestDTO;
import com.example.microservices.orderservice.dto.OrderResponseDTO;
import com.example.microservices.orderservice.entity.Order;
import com.example.microservices.orderservice.exception.ProductOutOfStockException;
import com.example.microservices.orderservice.mapper.OrderMapper;
import com.example.microservices.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final InventoryClient inventoryClient;

    @Transactional(rollbackFor = Exception.class)
    public void placeOrder(OrderRequestDTO requestDTO) {
        log.info("Placing order for skuCode: {}, quantity: {}", requestDTO.skuCode(), requestDTO.quantity());

        if (!inventoryClient.isInStock(requestDTO.skuCode(), requestDTO.quantity())) {
            log.warn("Order placement failed for skuCode: {} due to insufficient stock", requestDTO.skuCode());
            throw new ProductOutOfStockException(requestDTO.skuCode());
        }

        Order order = orderMapper.toEntity(requestDTO);
        orderRepository.save(order);
        log.info("Order placed successfully: {}", order);
    }


    @Transactional(readOnly = true)
    public List<OrderResponseDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }
}
