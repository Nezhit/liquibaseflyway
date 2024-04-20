package com.example.migrations.controllers;

import com.example.migrations.controllers.api.OrderApi;
import com.example.migrations.dto.GoodDto;
import com.example.migrations.dto.OrderDto;
import com.example.migrations.service.OrderService;
import org.springframework.http.ResponseEntity;

public class OrderController implements OrderApi {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @Override
    public ResponseEntity<?> getOrders() {
        return orderService.getOrders();
    }

    @Override
    public ResponseEntity<?> createOrder(OrderDto orderDto) {
        return orderService.createOrder(orderDto);
    }

    @Override
    public ResponseEntity<?> updateOrder(OrderDto orderDto) {
        return orderService.updateOrder(orderDto);
    }

    @Override
    public ResponseEntity<?> deleteOrder(Long id) {
        return orderService.deleteOrder(id);
    }
}
