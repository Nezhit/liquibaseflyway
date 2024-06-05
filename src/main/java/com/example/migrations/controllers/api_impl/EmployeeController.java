package com.example.migrations.controllers.api_impl;

import com.example.migrations.controllers.EmployeeApi;
import com.example.migrations.dto.EmployeeCreateDto;
import com.example.migrations.dto.EmployeeRsDto;
import com.example.migrations.dto.EmployeeUpdateDto;
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
    public ResponseEntity<List<EmployeeRsDto>> getEmployees() {
        List<EmployeeRsDto> employees = employeeService.getEmployees();
        return ResponseEntity.ok(employees);
    }

    @Override
    public ResponseEntity<EmployeeRsDto> createEmployee(EmployeeCreateDto employeeCreateDto) {
        EmployeeRsDto createdEmployee = employeeService.createEmployee(employeeCreateDto);
        return ResponseEntity.ok(createdEmployee);
    }

    @Override
    public ResponseEntity<EmployeeRsDto> updateEmployee(Long id, EmployeeUpdateDto employeeUpdateDto) {
            EmployeeRsDto updatedEmployee = employeeService.updateEmployee(id, employeeUpdateDto);
            return ResponseEntity.ok(updatedEmployee);
    }

    @Override
    public ResponseEntity<Void> deleteEmployee(Long id) {
            employeeService.deleteEmployee(id);
            return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<EmployeeRsDto> getEmployeeById(Long id) {
            EmployeeRsDto employee = employeeService.getEmployeeById(id);
            return ResponseEntity.ok(employee);
    }
}
