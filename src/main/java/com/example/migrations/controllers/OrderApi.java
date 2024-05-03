package com.example.migrations.controllers;

import com.example.migrations.dto.OrderCreateDto;
import com.example.migrations.dto.OrderRsDto;
import com.example.migrations.dto.OrderUpdateDto;
import com.example.migrations.entity.Order;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Get orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all orders"),
            @ApiResponse(responseCode = "404", description = "Orders not found or exception")
    })
    public List<OrderRsDto> getOrders();

    @PostMapping
    @Operation(summary = "Create order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order created"),
            @ApiResponse(responseCode = "404", description = "Order  not created")
    })
    public OrderRsDto createOrder(@RequestBody OrderCreateDto orderCreateDto);

    @PutMapping("/{id}")
    @Operation(summary = "Update orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order update"),
            @ApiResponse(responseCode = "404", description = "Order  not updated")
    })
    public OrderRsDto updateOrder(@PathVariable Long id, @RequestBody OrderUpdateDto orderUpdateDto);

    @DeleteMapping("/api/order/{id}")
    @Operation(summary = "Delete order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order deleted"),
            @ApiResponse(responseCode = "404", description = "Order not deleted")
    })
    public void deleteOrder(@PathVariable Long id);

    @GetMapping("/{id}")
    @Operation(summary = "Get order by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order found"),
            @ApiResponse(responseCode = "404", description = "Order not found or exception")
    })
    public OrderRsDto getOrderById(@PathVariable Long id);
}
