package com.example.migrations.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class EmployeeUpdateDto {
    private String name;
    private String surname;
    private String papaname;
    private LocalDate birthdate;
    private String address;
    private String phone;
    private Integer passport;
}
