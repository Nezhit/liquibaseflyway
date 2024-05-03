package com.example.migrations.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class EmployeeCreateDto {
    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Surname cannot be null")
    private String surname;

    @NotNull(message = "Papaname cannot be null")
    private String papaname;

    @NotNull(message = "Birthdate cannot be null")
    @Past(message = "Birthdate must be in the past")
    private LocalDate birthdate;

    @NotNull(message = "Address cannot be null")
    private String address;

    @NotNull(message = "Phone number cannot be null")
    private String phone;

    @NotNull(message = "Passport number cannot be null")
    private Integer passport;
}
