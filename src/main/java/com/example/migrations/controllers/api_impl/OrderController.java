package com.example.migrations.controllers.api_impl;

import com.example.migrations.controllers.OrderApi;
import com.example.migrations.dto.GoodRsDto;
import com.example.migrations.dto.OrderCreateDto;
import com.example.migrations.dto.OrderRsDto;
import com.example.migrations.dto.OrderUpdateDto;
import com.example.migrations.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class OrderController implements OrderApi {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public ResponseEntity<List<OrderRsDto>> getOrders() {
        List<OrderRsDto> orders = orderService.getOrders();
        return ResponseEntity.ok(orders);
    }

    @Override
    public ResponseEntity<OrderRsDto> createOrder(OrderCreateDto orderCreateDto) {
        OrderRsDto createdOrder = orderService.createOrder(orderCreateDto);
        return ResponseEntity.ok(createdOrder);
    }

    @Override
    public ResponseEntity<OrderRsDto> updateOrder(Long id, OrderUpdateDto orderUpdateDto) {
            OrderRsDto updatedOrder = orderService.updateOrder(id, orderUpdateDto);
            return ResponseEntity.ok(updatedOrder);
    }

    @Override
    public ResponseEntity<Void> deleteOrder(Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<OrderRsDto> getOrderById(Long id) {
        OrderRsDto orderRsDto= orderService.getOrderById(id);
        return ResponseEntity.ok(orderRsDto);
    }
}
