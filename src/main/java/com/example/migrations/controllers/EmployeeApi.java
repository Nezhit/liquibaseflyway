package com.example.migrations.controllers;

import com.example.migrations.dto.EmployeeCreateDto;
import com.example.migrations.dto.EmployeeRsDto;
import com.example.migrations.dto.EmployeeUpdateDto;
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

@Tag(name = "Employee API", description = "API for employee management")
@RequestMapping("/api/employee")
public interface EmployeeApi {
    @GetMapping
    @Operation(
            summary = "Get employees",
            method = "GET",
            tags = {"Employee API"},
            description = "Retrieve a list of all employees",
            operationId = "getEmployees"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employees found",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = EmployeeRsDto.class)))}),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public List<EmployeeRsDto> getEmployees();

    @PostMapping
    @Operation(
            summary = "Create employee",
            method = "POST",
            tags = {"Employee API"},
            description = "Create a new employee using the provided DTO",
            operationId = "createEmployee"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeRsDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid data provided"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public EmployeeRsDto createEmployee(@Schema(implementation = EmployeeCreateDto.class) @RequestBody EmployeeCreateDto employeeCreateDto);

    @PutMapping("/{id}")
    @Operation(
            summary = "Update employee",
            method = "PUT",
            tags = {"Employee API"},
            description = "Update an existing employee by ID using the provided DTO",
            operationId = "updateEmployee",
            parameters = @Parameter(name = "id", description = "ID of the employee to update", required = true, example = "1")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeRsDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Employee not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public EmployeeRsDto updateEmployee(@PathVariable Long id, @Schema(implementation = EmployeeUpdateDto.class) @RequestBody EmployeeUpdateDto employeeUpdateDto);

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete employee",
            method = "DELETE",
            tags = {"Employee API"},
            description = "Delete an employee by ID",
            operationId = "deleteEmployee",
            parameters = @Parameter(name = "id", description = "ID of the employee to delete", required = true, example = "1")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee deleted"),
            @ApiResponse(responseCode = "404", description = "Employee not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public void deleteEmployee(@PathVariable Long id);

    @GetMapping("/{id}")
    @Operation(
            summary = "Get employee by id",
            method = "GET",
            tags = {"Employee API"},
            description = "Retrieve an employee by their ID",
            operationId = "getEmployeeById",
            parameters = @Parameter(name = "id", description = "ID of the employee to get", required = true, example = "1")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeRsDto.class))}),
            @ApiResponse(responseCode = "404", description = "Employee not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public EmployeeRsDto getEmployeeById(@PathVariable Long id);
}
