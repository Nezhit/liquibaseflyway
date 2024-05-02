package com.example.migrations.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDto {

    private Long id;

    @NotNull(message = "Title cannot be null")
    private String title;

    @NotNull(message = "Address cannot be null")
    private String address;

    @NotNull(message = "Phone number cannot be null")
    private String phone;

    @Override
    public String toString() {
        return "CustomerDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
