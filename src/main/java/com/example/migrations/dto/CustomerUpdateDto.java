package com.example.migrations.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
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
