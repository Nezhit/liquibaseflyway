package com.example.migrations.controllers;

import com.example.migrations.controllers.api.OrderApi;
import com.example.migrations.dto.GoodDto;
import com.example.migrations.dto.OrderDto;
import com.example.migrations.entity.Order;
import com.example.migrations.service.OrderService;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class OrderController implements OrderApi {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @Override
    public List<Order> getOrders() {
        return orderService.getOrders();
    }

    @Override
    public Order createOrder(OrderDto orderDto) {
        return orderService.createOrder(orderDto);
    }

    @Override
    public Order updateOrder(Long id,OrderDto orderDto) {
        return orderService.updateOrder(id,orderDto);
    }

    @Override
    public Order deleteOrder(Long id) {
        return orderService.deleteOrder(id);
    }
}
