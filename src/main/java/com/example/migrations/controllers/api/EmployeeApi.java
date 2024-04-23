package com.example.migrations.controllers.api;

import com.example.migrations.dto.CustomerDto;
import com.example.migrations.dto.EmployeeDto;
import com.example.migrations.entity.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/employee")
public interface EmployeeApi {
    @GetMapping
    public List<Employee> getEmployees();
    @PostMapping
    public Employee createEmployee(@RequestBody EmployeeDto employeeDto);
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id,@RequestBody EmployeeDto employeeDto);
    @DeleteMapping("/{id}")
    public Employee deleteEmployee(@PathVariable Long id);
}
