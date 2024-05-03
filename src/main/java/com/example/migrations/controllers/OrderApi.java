package com.example.migrations.controllers;

import com.example.migrations.dto.OrderCreateDto;
import com.example.migrations.dto.OrderRsDto;
import com.example.migrations.dto.OrderUpdateDto;
import com.example.migrations.entity.Order;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@RequestMapping("/api/order")
public interface OrderApi {
    @GetMapping
    public List<OrderRsDto> getOrders();

    @PostMapping
    public OrderRsDto createOrder(@RequestBody OrderCreateDto orderCreateDto);

    @PutMapping("/{id}")
    public OrderRsDto updateOrder(@PathVariable Long id, @RequestBody OrderUpdateDto orderUpdateDto);

    @DeleteMapping("/api/order/{id}")
    public void deleteOrder(@PathVariable Long id);

    @GetMapping("/{id}")
    public OrderRsDto getOrderById(@PathVariable Long id);
}
