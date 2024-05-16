package com.example.migrations.dto;

import com.example.migrations.entity.Producer;
import com.example.migrations.entity.Type;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Schema(description = "Data Transfer Object for creating a new good, detailing its type, title, and associated producer.")
public class GoodCreateDto {
    @Schema(description = "Type of the good")
    private Type type;

    @Schema(description = "Title of the good", example = "Widget", required = true)
    @NotNull(message = "Title cannot be null")
    @Size(min = 1, max = 100, message = "Title must be between 1 and 100 characters")
    private String title;

    @Schema(description = "Producer of the good")
    private Producer producer;
}
