package com.example.migrations.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class CustomerUpdateDto {
    private String title;
    private String address;
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
