package com.example.migrations.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Schema(description = "Data Transfer Object for creating a new customer. It contains fields such as title, address, and phone number, all of which are required to properly identify and contact the customer.")
public class CustomerCreateDto {
    @Schema(description = "title of a customer", example = "John", required = true)
    @NotNull(message = "Title cannot be null")
    @Size(min = 2, max = 10, message = "Title must be between 2 and 10 characters")
    private String title;

    @Schema(description = "Customer's address", example = "st 12", required = true)
    @NotNull(message = "Address cannot be null")
    @Size(min = 10, max = 100, message = "Address must be between 10 and 100 characters")
    private String address;

    @Schema(description = "Customer's phone number", example = "89522812091", required = true)
    @NotNull(message = "Phone number cannot be null")
    @Pattern(regexp = "\\+?[0-9.\\-()]{10,25}", message = "Phone number is invalid")
    private String phone;

    @Override
    public String toString() {
        return "CustomerDto{" +
                ", title='" + title + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
