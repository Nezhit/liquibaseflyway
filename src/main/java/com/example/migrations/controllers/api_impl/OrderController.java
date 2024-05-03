package com.example.migrations.controllers.api_impl;

import com.example.migrations.controllers.OrderApi;
import com.example.migrations.dto.OrderCreateDto;
import com.example.migrations.dto.OrderRsDto;
import com.example.migrations.dto.OrderUpdateDto;
import com.example.migrations.entity.Order;
import com.example.migrations.service.OrderService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class OrderController implements OrderApi {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public List<OrderRsDto> getOrders() {
        return orderService.getOrders();
    }

    @Override
    public OrderRsDto createOrder(OrderCreateDto orderCreateDto) {
        return orderService.createOrder(orderCreateDto);
    }

    @Override
    public OrderRsDto updateOrder(Long id, OrderUpdateDto orderUpdateDto) {
        return orderService.updateOrder(id, orderUpdateDto);
    }

    @Override
    public void deleteOrder(Long id) {
        orderService.deleteOrder(id);
    }

    @Override
    public OrderRsDto getOrderById(Long id) {
        return orderService.getOrderById(id);
    }
}
