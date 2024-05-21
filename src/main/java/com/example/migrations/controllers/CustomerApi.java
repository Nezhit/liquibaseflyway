package com.example.migrations.controllers;

import com.example.migrations.dto.CustomerCreateDto;
import com.example.migrations.dto.CustomerRsDto;
import com.example.migrations.dto.CustomerUpdateDto;
import com.example.migrations.entity.Customer;
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

@Tag(name = "Customer API", description = "API for customer management")
@RequestMapping("/api/customer")
public interface CustomerApi {
    @GetMapping
    @Operation(
            summary = "Get all customers",
            method = "GET",
            tags = {"Customer API"},
            description = "Retrieve a list of all customers.",
            operationId = "getCustomers"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the customers", content =
                    {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CustomerRsDto.class)))}),
            @ApiResponse(responseCode = "500", description = "Customers not found or exception")
    })
    public List<CustomerRsDto> getCustomers();

    @PostMapping
    @Operation(
            summary = "Create Customer",
            method = "POST",
            tags = {"Customer API"},
            description = "Creating a customer using the provided DTO.",
            operationId = "createCustomer"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer created", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = CustomerRsDto.class))}),
            @ApiResponse(responseCode = "500", description = "Customer not created")
    })
    public CustomerRsDto createCustomer(@Schema(implementation = CustomerCreateDto.class) @RequestBody CustomerCreateDto customerCreateDto);

    @PutMapping("/{id}")
    @Operation(
            summary = "Update Customer",
            method = "PUT",
            tags = {"Customer API"},
            description = "Update an existing customer by ID using the provided DTO.",
            operationId = "updateCustomer",
            parameters = @Parameter(name = "id", description = "ID of the customer to update", required = true, example = "1")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer updated", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = CustomerRsDto.class))}),
            @ApiResponse(responseCode = "500", description = "Customer not updated")
    })
    public CustomerRsDto updateCustomer(@PathVariable Long id, @Schema(implementation = CustomerUpdateDto.class) @RequestBody CustomerUpdateDto customerUpdateDto);

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete Customer",
            method = "DELETE",
            tags = {"Customer API"},
            description = "Delete a customer by ID.",
            operationId = "deleteCustomer",
            parameters = @Parameter(name = "id", description = "ID of the customer to delete", required = true, example = "1")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer deleted"),
            @ApiResponse(responseCode = "500", description = "Customer not deleted")
    })
    public void deleteCustomer(@PathVariable Long id);

    @GetMapping("/{id}")
    @Operation(
            summary = "Find customer by id",
            method = "GET",
            tags = {"Customer API"},
            description = "Retrieve a customer by their ID.",
            operationId = "getCustomerById",
            parameters = @Parameter(name = "id", description = "ID of the customer to get", required = true, example = "1")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer found by id", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = CustomerRsDto.class))}),
            @ApiResponse(responseCode = "500", description = "Customer not found or exception")
    })
    public CustomerRsDto getCustomerById(Long id);
}
