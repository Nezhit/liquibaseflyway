package com.example.migrations.service;

import com.example.migrations.dto.EmployeeDto;
import com.example.migrations.dto.EmployeeUpdateDto;
import com.example.migrations.entity.Employee;
import com.example.migrations.repository.EmployeeRepo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Employee createEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(employeeDto);
        return employeeRepo.save(employee);
    }

    public Employee updateEmployee(Long id, EmployeeUpdateDto employeeUpdateDto) {
        Employee employee = employeeRepo.findById(employeeUpdateDto.getId()).get();
        if(employeeUpdateDto.getPassport()!=null) employee.setPassport(employeeUpdateDto.getPassport());
        if(employeeUpdateDto.getBirthdate()!=null) employee.setBirthdate(employeeUpdateDto.getBirthdate());
        if(employeeUpdateDto.getPhone()!=null) employee.setPhone(employeeUpdateDto.getPhone());
        if(employeeUpdateDto.getAddress()!=null) employee.setAddress(employeeUpdateDto.getAddress());
        if(employeeUpdateDto.getSurname()!=null) employee.setSurname(employeeUpdateDto.getSurname());
        if(employeeUpdateDto.getPapaname()!=null) employee.setPapaname(employeeUpdateDto.getPapaname());
        if(employeeUpdateDto.getName()!=null) employee.setName(employeeUpdateDto.getName());
        return employeeRepo.save(employee);
    }

    public List<Employee> getEmployees() {
        return employeeRepo.findAll();
    }

    public void deleteEmployee(Long id) {
        Employee employee = employeeRepo.findById(id).get();
        employeeRepo.delete(employee);
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepo.findById(id).get();
    }
}
