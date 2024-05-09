package com.example.migrations.dto;

import com.example.migrations.entity.enums.ETypes;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class TypeCreateDto {
    @NotNull(message = "Title cannot be null")
    private ETypes title;
    @JsonCreator
    public TypeCreateDto(@JsonProperty("title") ETypes title) {
        this.title = title;
    }
}
