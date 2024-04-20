package com.example.migrations.controllers.api;

import com.example.migrations.dto.GoodDto;
import com.example.migrations.dto.OrderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface OrderApi {
    @GetMapping("/api/getOrders")
    public ResponseEntity<?> getOrders();
    @PostMapping("/api/createOrder")
    public ResponseEntity<?>createOrder(@RequestBody OrderDto orderDto);
    @PutMapping("/api/updateOrder")
    public ResponseEntity<?> updateOrder(@RequestBody OrderDto orderDto);
    @DeleteMapping("/api/deleteOrder/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id);
}
