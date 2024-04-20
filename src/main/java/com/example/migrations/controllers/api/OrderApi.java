package com.example.migrations.controllers.api;

import com.example.migrations.dto.GoodDto;
import com.example.migrations.dto.OrderDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Get orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all orders"),
            @ApiResponse(responseCode = "404", description = "Orders not found or exception")
    })
    public ResponseEntity<?> getOrders();
    @PostMapping("/api/createOrder")
    @Operation(summary = "Create order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order created"),
            @ApiResponse(responseCode = "404", description = "Order  not created")
    })
    public ResponseEntity<?>createOrder(@RequestBody OrderDto orderDto);
    @PutMapping("/api/updateOrder")
    @Operation(summary = "Update orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order update"),
            @ApiResponse(responseCode = "404", description = "Order  not updated")
    })
    public ResponseEntity<?> updateOrder(@RequestBody OrderDto orderDto);
    @DeleteMapping("/api/deleteOrder/{id}")
    @Operation(summary = "Delete order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order deleted"),
            @ApiResponse(responseCode = "404", description = "Order not deleted")
    })
    public ResponseEntity<?> deleteOrder(@PathVariable Long id);
}
