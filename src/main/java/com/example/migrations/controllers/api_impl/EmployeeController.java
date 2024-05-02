package com.example.migrations.controllers.api_impl;

import com.example.migrations.controllers.EmployeeApi;
import com.example.migrations.dto.EmployeeDto;
import com.example.migrations.dto.EmployeeUpdateDto;
import com.example.migrations.entity.Employee;
import com.example.migrations.service.EmployeeService;
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
    public Employee updateEmployee(Long id, EmployeeUpdateDto employeeUpdateDto) {
        return employeeService.updateEmployee(id, employeeUpdateDto);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeService.deleteEmployee(id);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeService.getEmployeeById(id);
    }
}
