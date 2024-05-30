package com.example.migrations.dto;

import lombok.Getter;
import java.time.LocalDate;

@Getter
public class EmployeeUpdateDto {
    private String name;
    private String surname;
    private String papaname;
    private LocalDate birthdate;
    private String address;
    private String phone;
    private Integer passport;
}
