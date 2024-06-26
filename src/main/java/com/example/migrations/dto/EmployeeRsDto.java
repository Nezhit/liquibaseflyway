package com.example.migrations.dto;

import com.example.migrations.entity.Employee;
import lombok.Getter;
import java.time.LocalDate;

@Getter
public class EmployeeRsDto {
    private String name;
    private String surname;
    private String papaname;
    private LocalDate birthdate;
    private String address;
    private String phone;
    private Integer passport;

    public EmployeeRsDto(Employee employee) {
        this.name = employee.getName();
        this.surname = employee.getSurname();
        this.papaname = employee.getPapaname();
        this.birthdate = employee.getBirthdate();
        this.address = employee.getAddress();
        this.phone = employee.getPhone();
        this.passport = employee.getPassport();
    }
}
