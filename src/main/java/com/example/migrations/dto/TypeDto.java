package com.example.migrations.dto;

import com.example.migrations.entity.enums.ETypes;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TypeDto {
    @Schema(description = "Unique identifier of the type", example = "1")
    private Long id;

    @Schema(description = "Title of the type")
    private ETypes Title;
}
