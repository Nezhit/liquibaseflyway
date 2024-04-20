package com.example.migrations.service;

import com.example.migrations.dto.EmployeeDto;
import com.example.migrations.entity.Employee;
import com.example.migrations.repository.EmployeeRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public ResponseEntity<?> createEmployee(EmployeeDto employeeDto) {
        Employee employee=new Employee();
        employee.setPassport(employeeDto.getPassport());
        employee.setBirthdate(employeeDto.getBirthdate());
        employee.setPhone(employeeDto.getPhone());
        employee.setAddress(employeeDto.getAddress());
        employee.setSurname(employeeDto.getSurname());
        employee.setPapaname(employeeDto.getPapaname());
        employee.setName(employeeDto.getName());
        employeeRepo.save(employee);
        return ResponseEntity.ok("Сотрудник создан");
    }

    public ResponseEntity<?> updateEmployee(EmployeeDto employeeDto) {
        Employee employee=employeeRepo.findById(employeeDto.getId()).get();
        employee.setPassport(employeeDto.getPassport());
        employee.setBirthdate(employeeDto.getBirthdate());
        employee.setPhone(employeeDto.getPhone());
        employee.setAddress(employeeDto.getAddress());
        employee.setSurname(employeeDto.getSurname());
        employee.setPapaname(employeeDto.getPapaname());
        employee.setName(employeeDto.getName());
        employeeRepo.save(employee);
        return ResponseEntity.ok("Сотрудник обновлен");
    }
}
