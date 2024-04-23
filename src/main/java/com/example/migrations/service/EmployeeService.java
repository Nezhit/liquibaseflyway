package com.example.migrations.service;

import com.example.migrations.dto.EmployeeDto;
import com.example.migrations.entity.Employee;
import com.example.migrations.repository.EmployeeRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Employee createEmployee(EmployeeDto employeeDto) {
        Employee employee=new Employee();
        employee.setPassport(employeeDto.getPassport());
        employee.setBirthdate(employeeDto.getBirthdate());
        employee.setPhone(employeeDto.getPhone());
        employee.setAddress(employeeDto.getAddress());
        employee.setSurname(employeeDto.getSurname());
        employee.setPapaname(employeeDto.getPapaname());
        employee.setName(employeeDto.getName());
        employeeRepo.save(employee);
        return employee;
    }

    public Employee updateEmployee(Long id,EmployeeDto employeeDto) {
        Employee employee=employeeRepo.findById(employeeDto.getId()).get();
        employee.setPassport(employeeDto.getPassport());
        employee.setBirthdate(employeeDto.getBirthdate());
        employee.setPhone(employeeDto.getPhone());
        employee.setAddress(employeeDto.getAddress());
        employee.setSurname(employeeDto.getSurname());
        employee.setPapaname(employeeDto.getPapaname());
        employee.setName(employeeDto.getName());
        employeeRepo.save(employee);
        return employee;
    }

    public List<Employee> getEmployees() {
        return employeeRepo.findAll();
    }

    public Employee deleteEmployee(Long id) {
        Employee employee = employeeRepo.findById(id).get();
        employeeRepo.delete(employee);
        return employee;
    }
}
