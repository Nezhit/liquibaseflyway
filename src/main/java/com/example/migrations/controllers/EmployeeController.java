package com.example.migrations.controllers;

import com.example.migrations.controllers.api.EmployeeApi;
import com.example.migrations.dto.CustomerDto;
import com.example.migrations.dto.EmployeeDto;
import com.example.migrations.repository.EmployeeRepo;
import com.example.migrations.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
    public ResponseEntity<?> createEmployee(EmployeeDto employeeDto) {
        return employeeService.createEmployee(employeeDto);
    }

    @Override
    public ResponseEntity<?> updateEmployee(EmployeeDto employeeDto) {
        return employeeService.updateEmployee(employeeDto);
    }

    @Override
    public ResponseEntity<?> deleteEmployee(Long id) {
        return null;
    }
}
