package com.example.migrations.dto;

import com.example.migrations.entity.enums.ETypes;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TypeDto {

    private Long id;

    @NotNull(message = "Title cannot be null")
    private ETypes Title;
}
