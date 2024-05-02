package com.example.migrations.controllers.api_impl;

import com.example.migrations.controllers.OrderApi;
import com.example.migrations.dto.OrderDto;
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
    public List<Order> getOrders() {
        return orderService.getOrders();
    }

    @Override
    public Order createOrder(OrderDto orderDto) {
        return orderService.createOrder(orderDto);
    }

    @Override
    public Order updateOrder(Long id, OrderUpdateDto orderUpdateDto) {
        return orderService.updateOrder(id, orderUpdateDto);
    }

    @Override
    public void deleteOrder(Long id) {
        orderService.deleteOrder(id);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderService.getOrderById(id);
    }
}
