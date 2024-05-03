package com.example.migrations.dto;

import com.example.migrations.entity.enums.ETypes;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TypeUpdateDto {
    private ETypes Title;
}
