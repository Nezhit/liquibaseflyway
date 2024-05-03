package com.example.migrations.controllers;

import com.example.migrations.dto.EmployeeCreateDto;
import com.example.migrations.dto.EmployeeRsDto;
import com.example.migrations.dto.EmployeeUpdateDto;
import com.example.migrations.entity.Employee;
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
    public List<EmployeeRsDto> getEmployees();

    @PostMapping
    public EmployeeRsDto createEmployee(@RequestBody EmployeeCreateDto employeeCreateDto);

    @PutMapping("/{id}")
    public EmployeeRsDto updateEmployee(@PathVariable Long id, @RequestBody EmployeeUpdateDto employeeUpdateDto);

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id);

    @GetMapping("/{id}")
    public EmployeeRsDto getEmployeeById(@PathVariable Long id);
}
