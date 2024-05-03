package com.example.migrations.dto;

import com.example.migrations.entity.enums.ETypes;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TypeCreateDto {
    @NotNull(message = "Title cannot be null")
    private ETypes Title;
}
