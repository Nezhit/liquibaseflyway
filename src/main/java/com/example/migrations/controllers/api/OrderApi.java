package com.example.migrations.controllers.api;

import com.example.migrations.dto.GoodDto;
import com.example.migrations.dto.OrderDto;
import com.example.migrations.entity.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public interface OrderApi {
    @GetMapping
    public List<Order> getOrders();
    @PostMapping
    public Order createOrder(@RequestBody OrderDto orderDto);
    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id,@RequestBody OrderDto orderDto);
    @DeleteMapping("/api/order/{id}")
    public Order deleteOrder(@PathVariable Long id);
}
