package com.example.migrations.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProducerUpdateDto {
    private Long id;
    private String title;
    private String address;
    private String phone;
}
