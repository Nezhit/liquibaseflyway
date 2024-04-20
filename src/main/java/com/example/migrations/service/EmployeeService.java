package com.example.migrations.service;

import com.example.migrations.dto.EmployeeCreateDto;
import com.example.migrations.dto.EmployeeRsDto;
import com.example.migrations.dto.EmployeeUpdateDto;
import com.example.migrations.entity.Employee;
import com.example.migrations.repository.EmployeeRepo;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Tag(name = "Employee service", description = "Service providing basci CRUD operations")
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public EmployeeRsDto createEmployee(EmployeeCreateDto employeeCreateDto) {
        Employee employee = new Employee(employeeCreateDto);
        return new EmployeeRsDto(employeeRepo.save(employee));
    }

    public EmployeeRsDto updateEmployee(Long id, EmployeeUpdateDto employeeUpdateDto) {
        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new RuntimeException("Сотрудник не найден"));
        if (employeeUpdateDto.getPassport() != null) employee.setPassport(employeeUpdateDto.getPassport());
        if (employeeUpdateDto.getBirthdate() != null) employee.setBirthdate(employeeUpdateDto.getBirthdate());
        if (employeeUpdateDto.getPhone() != null) employee.setPhone(employeeUpdateDto.getPhone());
        if (employeeUpdateDto.getAddress() != null) employee.setAddress(employeeUpdateDto.getAddress());
        if (employeeUpdateDto.getSurname() != null) employee.setSurname(employeeUpdateDto.getSurname());
        if (employeeUpdateDto.getPapaname() != null) employee.setPapaname(employeeUpdateDto.getPapaname());
        if (employeeUpdateDto.getName() != null) employee.setName(employeeUpdateDto.getName());
        return new EmployeeRsDto(employeeRepo.save(employee));
    }

    public List<EmployeeRsDto> getEmployees() {
        return employeeRepo.findAll().stream().map(EmployeeRsDto::new).collect(Collectors.toList());
    }

    public void deleteEmployee(Long id) {
        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new RuntimeException("Сотрудник не найден"));
        employeeRepo.delete(employee);
    }

    public EmployeeRsDto getEmployeeById(Long id) {
        return new EmployeeRsDto(employeeRepo.findById(id).orElseThrow(() -> new RuntimeException("Сотрудник не найден")));
    }
}

