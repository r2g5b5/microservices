package com.example.microservices.orderservice.service;

import com.example.microservices.orderservice.dto.OrderRequestDTO;
import com.example.microservices.orderservice.dto.OrderResponseDTO;
import com.example.microservices.orderservice.entity.Order;
import com.example.microservices.orderservice.mapper.OrderMapper;
import com.example.microservices.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Transactional(rollbackFor = Exception.class)
    public void placeOrder(OrderRequestDTO requestDTO) {
        Order order = orderMapper.toEntity(requestDTO);
        orderRepository.save(order);
        log.info("Placed order: {}", order);
    }

    @Transactional(readOnly = true)
    public List<OrderResponseDTO> getAllOrders() {
        return null;
    }
}
