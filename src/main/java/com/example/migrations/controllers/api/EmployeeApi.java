package com.example.migrations.controllers.api;

import com.example.migrations.dto.CustomerDto;
import com.example.migrations.dto.EmployeeDto;
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
public interface EmployeeApi {
    @GetMapping("/api/getEmployees")
    @Operation(summary = "Get employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Found employees"),
            @ApiResponse(responseCode = "404", description = "Employees not found or exception")
    })
    public ResponseEntity<?> getEmployees();
    @PostMapping("/api/createEmployee")
    @Operation(summary = "Create employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Employee created"),
            @ApiResponse(responseCode = "404",description = "Employee not created")
    })
    public ResponseEntity<?>createEmployee(@RequestBody EmployeeDto employeeDto);
    @PutMapping("/api/updateEmployee")
    @Operation(summary = "Update employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Employee updated"),
            @ApiResponse(responseCode = "404", description = "Employee not updated")
    })
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeDto employeeDto);
    @DeleteMapping("/api/deleteEmployee/{id}")
    @Operation(summary = "Delete employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee deleted"),
            @ApiResponse(responseCode = "404", description = "Employee not deleted")
    })
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id);
}
