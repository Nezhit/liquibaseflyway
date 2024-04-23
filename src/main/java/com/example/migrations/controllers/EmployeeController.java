package com.example.migrations.controllers;

import com.example.migrations.controllers.api.EmployeeApi;
import com.example.migrations.dto.CustomerDto;
import com.example.migrations.dto.EmployeeDto;
import com.example.migrations.entity.Employee;
import com.example.migrations.repository.EmployeeRepo;
import com.example.migrations.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController implements EmployeeApi {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @Override
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @Override
    public Employee createEmployee(EmployeeDto employeeDto) {
        return employeeService.createEmployee(employeeDto);
    }

    @Override
    public Employee updateEmployee(Long id,EmployeeDto employeeDto) {
        return employeeService.updateEmployee(id,employeeDto);
    }

    @Override
    public Employee deleteEmployee(Long id) {
        return employeeService.deleteEmployee(id);
    }
}
