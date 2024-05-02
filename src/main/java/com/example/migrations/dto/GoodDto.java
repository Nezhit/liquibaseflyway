package com.example.migrations.dto;

import com.example.migrations.entity.Producer;
import com.example.migrations.entity.Type;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GoodDto {

    private Long id;

    @NotNull(message = "Type cannot be null")
    private Type type;

    @NotNull(message = "Title cannot be null")
    private String title;

    @NotNull(message = "Producer cannot be null")
    private Producer producer;
}
