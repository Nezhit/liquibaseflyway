package com.example.migrations.dto;

import com.example.migrations.entity.Type;
import com.example.migrations.entity.enums.ETypes;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class TypeRsDto {
    private ETypes title;

    public TypeRsDto(Type type){
        this.title=type.getTitle();
    }
}
