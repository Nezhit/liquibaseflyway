package com.example.migrations.dto;

import com.example.migrations.entity.Type;
import com.example.migrations.entity.enums.ETypes;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class TypeRsDto {
    private ETypes Title;

    public TypeRsDto(Type type){
        this.Title=type.getTitle();
    }
}
