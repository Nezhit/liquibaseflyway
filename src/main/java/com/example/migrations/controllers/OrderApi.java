package com.example.migrations.controllers;

import com.example.migrations.dto.EmployeeRsDto;
import com.example.migrations.dto.EmployeeUpdateDto;
import com.example.migrations.dto.OrderCreateDto;
import com.example.migrations.dto.OrderRsDto;
import com.example.migrations.dto.OrderUpdateDto;
import com.example.migrations.entity.Order;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
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

@Tag(name = "Order API", description = "API for order management")
@RequestMapping("/api/order")
public interface OrderApi {
    @GetMapping
    @Operation(
            summary = "Get orders",
            method = "GET",
            tags = {"Order API"},
            description = "Retrieve a list of all orders.",
            operationId = "getOrders"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all orders", content =
                    {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = OrderRsDto.class)))}),
            @ApiResponse(responseCode = "500", description = "Orders not found or exception")
    })
    public List<OrderRsDto> getOrders();

    @PostMapping
    @Operation(
            summary = "Create order",
            method = "POST",
            tags = {"Order API"},
            description = "Create a new order using the provided DTO.",
            operationId = "createOrder"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order successfully created"),
            @ApiResponse(responseCode = "500", description = "Order not created")
    })
    public OrderRsDto createOrder(@Schema(implementation = OrderCreateDto.class) @RequestBody OrderCreateDto orderCreateDto);

    @PutMapping("/{id}")
    @Operation(
            summary = "Update order",
            method = "PUT",
            tags = {"Order API"},
            description = "Update an existing order by ID using the provided DTO.",
            operationId = "updateOrder",
            parameters = @Parameter(name = "id", description = "ID of the order to update", required = true, example = "1")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order successfully updated"),
            @ApiResponse(responseCode = "500", description = "Order not updated")
    })
    public OrderRsDto updateOrder(@PathVariable Long id, @Schema(implementation = OrderUpdateDto.class) @RequestBody OrderUpdateDto orderUpdateDto);

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete order",
            method = "DELETE",
            tags = {"Order API"},
            description = "Delete an order by ID.",
            operationId = "deleteOrder",
            parameters = @Parameter(name = "id", description = "ID of the order to delete", required = true, example = "1")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order successfully deleted"),
            @ApiResponse(responseCode = "500", description = "Order not deleted")
    })
    public void deleteOrder(@PathVariable Long id);

    @GetMapping("/{id}")
    @Operation(
            summary = "Get order by id",
            method = "GET",
            tags = {"Order API"},
            description = "Retrieve an order by its ID.",
            operationId = "getOrderById",
            parameters = @Parameter(name = "id", description = "ID of the order to get", required = true, example = "1")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order found"),
            @ApiResponse(responseCode = "500", description = "Order not found or exception")
    })
    public OrderRsDto getOrderById(@PathVariable Long id);
}

