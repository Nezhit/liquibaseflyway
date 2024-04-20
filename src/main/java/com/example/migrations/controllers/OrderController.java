package com.example.migrations.controllers;

import com.example.migrations.controllers.api.OrderApi;
import com.example.migrations.dto.GoodDto;
import com.example.migrations.dto.OrderDto;
import com.example.migrations.service.OrderService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

@Tag(name = "Order controller", description = "Implementation of order API")
@Validated
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
    public ResponseEntity<?> createOrder(@Parameter(name = "OrderDTO",
            description = "Data transfer object of a order")
                                             @Valid
                                             OrderDto orderDto) {
        return orderService.createOrder(orderDto);
    }

    @Override
    public ResponseEntity<?> updateOrder(@Parameter(name = "OrderDTO",
            description = "Data transfer object of a order")
                                             @Valid
                                             OrderDto orderDto) {
        return orderService.updateOrder(orderDto);
    }

    @Override
    public ResponseEntity<?> deleteOrder(@Parameter(name = "id",
            description = "Identificator of order")
                                             Long id) {
        return orderService.deleteOrder(id);
    }
}
