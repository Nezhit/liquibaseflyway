package com.example.migrations.controllers.api_impl;

import com.example.migrations.controllers.TypeApi;
import com.example.migrations.dto.TypeDto;
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
    public List<Type> getTypes() {
        return typeService.getTypes();
    }

    @Override
    public Type createType(TypeDto typeDto) {
        return typeService.createType(typeDto);
    }

    @Override
    public Type updateType(Long id, TypeDto typeDto) {
        return typeService.updateType(id, typeDto);
    }

    @Override
    public void deleteType(Long id) {
        typeService.deleteType(id);
    }

    @Override
    public Type getTypeById(Long id) {
        return typeService.getTypeById(id);
    }
}
