package com.example.migrations.dto;

import com.example.migrations.entity.enums.ETypes;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TypeDto {

    private Long id;

    private ETypes Title;
}
