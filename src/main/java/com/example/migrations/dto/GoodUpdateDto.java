package com.example.migrations.dto;

import com.example.migrations.entity.Producer;
import com.example.migrations.entity.Type;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class GoodUpdateDto {
    private Type type;
    private String title;
    private Producer producer;
}
