package com.example.migrations.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Schema(description = "Data Transfer Object for registering a new producer")
public class ProducerCreateDto {
    @Schema(description = "Title of the producer", example = "BestGoods Inc.", required = true)
    @NotNull(message = "Title cannot be null")
    @Size(min = 1, max = 100, message = "Title must be between 1 and 100 characters")
    private String title;

    @Schema(description = "Address of the producer", example = "1234 Industrial Way, Example City, EC 12345", required = true)
    @NotNull(message = "Address cannot be null")
    @Size(min = 10, max = 150, message = "Address must be between 10 and 150 characters")
    private String address;

    @Schema(description = "Phone number of the producer", example = "+1234567890", required = true)
    @NotNull(message = "Phone cannot be null")
    @Size(min = 10, max = 25, message = "Phone must be between 10 and 25 characters")
    private String phone;
}
