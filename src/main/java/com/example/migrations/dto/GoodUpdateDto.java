package com.example.migrations.dto;

import com.example.migrations.entity.Producer;
import com.example.migrations.entity.Type;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GoodUpdateDto {
    private Long id;
    private Type type;
    private String title;
    private Producer producer;
}
