package com.example.migrations.controllers.api;

import com.example.migrations.dto.CustomerDto;
import com.example.migrations.entity.Customer;
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
public interface CustomerApi {

    @GetMapping("/api/getCustomers")
    @Operation(summary = "Get all customers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the customers"),
            @ApiResponse(responseCode = "404", description = "Customers not found or exception")
    })
    public ResponseEntity<?> getCustomers();
    @PostMapping("/api/createCustomer")
    @Operation(summary = "Create Customer",description = "Creating customer using dto ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer created"),
            @ApiResponse(responseCode = "404", description = "Customer not created")
    })
    public ResponseEntity<?>createCustomer(@RequestBody CustomerDto customerDto);
    @PutMapping("/api/updateCustomer")
    @Operation(summary = "Update Customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Customer updated"),
            @ApiResponse(responseCode = "404",description = "Customer not updated")
    })
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerDto customerDto);
    @DeleteMapping("/api/deleteCustomer/{id}")
    @Operation(summary = "Delete Customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer deleted"),
            @ApiResponse(responseCode = "404", description = "Customer not deleted")
    })
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id);

}
