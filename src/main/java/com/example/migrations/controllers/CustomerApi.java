package com.example.migrations.controllers;

import com.example.migrations.dto.CustomerCreateDto;
import com.example.migrations.dto.CustomerRsDto;
import com.example.migrations.dto.CustomerUpdateDto;
import com.example.migrations.entity.Customer;
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

@RequestMapping("/api/customer")
public interface CustomerApi {
    @GetMapping
    @Operation(summary = "Get all customers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the customers"),
            @ApiResponse(responseCode = "404", description = "Customers not found or exception")
    })
    public List<CustomerRsDto> getCustomers();

    @PostMapping
    @Operation(summary = "Create Customer",description = "Creating customer using dto ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer created"),
            @ApiResponse(responseCode = "404", description = "Customer not created")
    })
    public CustomerRsDto createCustomer(@RequestBody CustomerCreateDto customerCreateDto);

    @PutMapping("/{id}")
    @Operation(summary = "Update Customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Customer updated"),
            @ApiResponse(responseCode = "404",description = "Customer not updated")
    })
    public CustomerRsDto updateCustomer(@PathVariable Long id, @RequestBody CustomerUpdateDto customerUpdateDto);

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer deleted"),
            @ApiResponse(responseCode = "404", description = "Customer not deleted")
    })
    public void deleteCustomer(@PathVariable Long id);

    @GetMapping("/{id}")
    @Operation(summary = "Find customer by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer found by id"),
            @ApiResponse(responseCode = "404", description = "Customer not found or exception")
    })
    public CustomerRsDto getCustomerById(Long id);
}
