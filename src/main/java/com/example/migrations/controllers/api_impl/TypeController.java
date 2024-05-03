package com.example.migrations.controllers.api_impl;

import com.example.migrations.controllers.TypeApi;
import com.example.migrations.dto.TypeCreateDto;
import com.example.migrations.dto.TypeRsDto;
import com.example.migrations.dto.TypeUpdateDto;
import com.example.migrations.entity.Type;
import com.example.migrations.service.TypeService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class TypeController implements TypeApi {
    private final TypeService typeService;

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @Override
    public List<TypeRsDto> getTypes() {
        return typeService.getTypes();
    }

    @Override
    public TypeRsDto createType(TypeCreateDto typeCreateDto) {
        return typeService.createType(typeCreateDto);
    }

    @Override
    public TypeRsDto updateType(Long id, TypeUpdateDto typeUpdateDto) {
        return typeService.updateType(id, typeUpdateDto);
    }

    @Override
    public void deleteType(Long id) {
        typeService.deleteType(id);
    }

    @Override
    public TypeRsDto getTypeById(Long id) {
        return typeService.getTypeById(id);
    }
}
