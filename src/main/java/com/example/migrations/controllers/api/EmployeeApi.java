package com.example.migrations.controllers.api;

import com.example.migrations.dto.CustomerDto;
import com.example.migrations.dto.EmployeeDto;
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
    public ResponseEntity<?> getEmployees();
    @PostMapping("/api/createEmployee")
    public ResponseEntity<?>createEmployee(@RequestBody EmployeeDto employeeDto);
    @PutMapping("/api/updateEmployee")
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeDto employeeDto);
    @DeleteMapping("/api/deleteEmployee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id);
}
