package com.example.migrations.controllers;

import com.example.migrations.dto.EmployeeUpdateDto;
import com.example.migrations.dto.OrderCreateDto;
import com.example.migrations.dto.OrderRsDto;
import com.example.migrations.dto.OrderUpdateDto;
import com.example.migrations.entity.Order;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@RequestMapping("/api/order")
@Tag(name = "Order API", description = "API for order management")
public interface OrderApi {
    @GetMapping
    @Operation(summary = "Get orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all orders"),
            @ApiResponse(responseCode = "404", description = "Orders not found or exception")
    })
    public List<OrderRsDto> getOrders();

    @PostMapping
    @Operation(summary = "Create order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order successfully created"),
            @ApiResponse(responseCode = "404", description = "Order not created")
    })
    public OrderRsDto createOrder(@Schema(implementation = OrderCreateDto.class)@RequestBody OrderCreateDto orderCreateDto);

    @PutMapping("/{id}")
    @Operation(summary = "Update order",
            parameters = @Parameter(name = "id", description = "ID of the customer to update", required = true, example = "1"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order successfully updated"),
            @ApiResponse(responseCode = "404", description = "Order not updated")
    })
    public OrderRsDto updateOrder(@PathVariable Long id, @Schema(implementation = OrderUpdateDto.class)@RequestBody OrderUpdateDto orderUpdateDto);

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete order",
            parameters = @Parameter(name = "id", description = "ID of the customer to delete", required = true, example = "1"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Order not deleted")
    })
    public void deleteOrder(@PathVariable Long id);

    @GetMapping("/{id}")
    @Operation(summary = "Get order by id",
            parameters = @Parameter(name = "id", description = "ID of the customer to get", required = true, example = "1"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order found"),
            @ApiResponse(responseCode = "404", description = "Order not found or exception")
    })
    public OrderRsDto getOrderById(@PathVariable Long id);
}

