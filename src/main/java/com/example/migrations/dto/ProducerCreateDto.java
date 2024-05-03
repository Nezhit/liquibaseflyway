package com.example.migrations.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class ProducerCreateDto {
    @NotNull(message = "Title cannot be null")
    private String title;

    @NotNull(message = "Address cannot be null")
    private String address;

    @NotNull(message = "Phone cannot be null")
    private String phone;
}
