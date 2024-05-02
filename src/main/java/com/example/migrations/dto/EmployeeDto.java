package com.example.migrations.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDto {

    private Long id;
    private String name;
    private String surname;
    private String papaname;
    private LocalDate birthdate;
    private String address;
    private String phone;
    private Integer passport;
}
