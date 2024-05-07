package com.example.migrations.controllers;

import com.example.migrations.dto.CustomerRsDto;
import com.example.migrations.dto.EmployeeCreateDto;
import com.example.migrations.dto.EmployeeRsDto;
import com.example.migrations.dto.EmployeeUpdateDto;
import com.example.migrations.entity.Employee;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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

@RequestMapping("/api/employee")
public interface EmployeeApi {
    @GetMapping
    @Operation(summary = "Get employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Found employees",content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = List.class)) }),
            @ApiResponse(responseCode = "404", description = "Employees not found or exception")
    })
    public List<EmployeeRsDto> getEmployees();

    @PostMapping
    @Operation(summary = "Create employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Employee created",content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = EmployeeRsDto.class)) }),
            @ApiResponse(responseCode = "404",description = "Employee not created")
    })
    public EmployeeRsDto createEmployee(@RequestBody EmployeeCreateDto employeeCreateDto);

    @PutMapping("/{id}")
    @Operation(summary = "Update employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Employee updated",content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = EmployeeRsDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Employee not updated")
    })
    public EmployeeRsDto updateEmployee(@PathVariable Long id, @RequestBody EmployeeUpdateDto employeeUpdateDto);

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee deleted"),
            @ApiResponse(responseCode = "404", description = "Employee not deleted")
    })
    public void deleteEmployee(@PathVariable Long id);

    @GetMapping("/{id}")
    @Operation(summary = "Get employee by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee found",content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = EmployeeRsDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Employee not found or exception")
    })
    public EmployeeRsDto getEmployeeById(@PathVariable Long id);
}
