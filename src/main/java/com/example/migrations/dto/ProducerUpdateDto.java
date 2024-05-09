package com.example.migrations.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
public class ProducerUpdateDto {
    private String title;
    private String address;
    private String phone;
}
