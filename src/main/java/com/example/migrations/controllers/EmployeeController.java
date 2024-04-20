package com.example.migrations.controllers;

import com.example.migrations.controllers.api.EmployeeApi;
import com.example.migrations.dto.CustomerDto;
import com.example.migrations.dto.EmployeeDto;
import com.example.migrations.repository.EmployeeRepo;
import com.example.migrations.service.EmployeeService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@Tag(name = "Employee controller", description = "Implementation of employee API")
public class EmployeeController implements EmployeeApi {
    private final EmployeeService employeeService;
    private final EmployeeRepo employeeRepo;

    public EmployeeController(EmployeeService employeeService, EmployeeRepo employeeRepo) {
        this.employeeService = employeeService;
        this.employeeRepo = employeeRepo;
    }

    @Override
    public ResponseEntity<?> getEmployees() {
        return ResponseEntity.ok(employeeRepo.findAll());
    }

    @Override
    public ResponseEntity<?> createEmployee(@Parameter(name = "EmployeeDTO",
            description = "Data transfer object of a employee")
                                                @Valid
                                                EmployeeDto employeeDto) {
        return employeeService.createEmployee(employeeDto);
    }

    @Override
    public ResponseEntity<?> updateEmployee(@Parameter(name = "EmployeeDTO",
            description = "Data transfer object of a employee")
                                                @Valid
                                                EmployeeDto employeeDto) {
        return employeeService.updateEmployee(employeeDto);
    }

    @Override
    public ResponseEntity<?> deleteEmployee(@Parameter(name = "id",
            description = "Identificator of employee")
                                                Long id) {
        return null;
    }
}
