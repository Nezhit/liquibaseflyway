package com.example.migrations.dto;

import com.example.migrations.entity.enums.ETypes;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class TypeUpdateDto {
    private ETypes title;
    @JsonCreator
    public TypeUpdateDto(@JsonProperty("title") ETypes title) {
        this.title = title;
    }
}
