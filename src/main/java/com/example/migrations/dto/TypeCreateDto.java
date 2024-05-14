package com.example.migrations.dto;

import com.example.migrations.entity.enums.ETypes;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Schema(description = "Data Transfer Object for defining a new type of good. Primarily used for categorization within an inventory system.")
public class TypeCreateDto {
    @NotNull(message = "Title cannot be null")
    @Schema(description = "Title of the type")
    private ETypes Title;
}
