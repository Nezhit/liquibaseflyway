package com.example.migrations.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Getter
@Schema(description = "Data Transfer Object for creating a new employee record")
public class EmployeeCreateDto {
    @Schema(description = "First name of the employee", example = "John", required = true)
    @NotNull(message = "Name cannot be null")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @Schema(description = "Surname of the employee", example = "Doe", required = true)
    @NotNull(message = "Surname cannot be null")
    @Size(min = 2, max = 50, message = "Surname must be between 2 and 50 characters")
    private String surname;

    @Schema(description = "Patronymic of the employee", example = "Michaelovich")
    @Size(min = 2, max = 50, message = "Papaname must be between 2 and 50 characters")
    private String papaname;

    @Schema(description = "Birthdate of the employee", example = "1985-04-12", required = true)
    @NotNull(message = "Birthdate cannot be null")
    @Past(message = "Birthdate must be in the past")
    private LocalDate birthdate;

    @Schema(description = "Address of the employee", example = "st 1", required = true)
    @NotNull(message = "Address cannot be null")
    @Size(min = 10, max = 100, message = "Address must be between 10 and 100 characters")
    private String address;

    @Schema(description = "Phone number of the employee", example = "12345678901", required = true)
    @NotNull(message = "Phone number cannot be null")
    @Pattern(regexp = "\\+?[0-9.\\-()]{10,25}", message = "Phone number is invalid")
    private String phone;

    @Schema(description = "Passport number of the employee", example = "123456789", required = true)
    @NotNull(message = "Passport number cannot be null")
    private Integer passport;
}
