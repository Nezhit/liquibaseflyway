package com.example.migrations.controllers;

import com.example.migrations.controllers.api.TypeApi;
import com.example.migrations.dto.TypeDto;
import com.example.migrations.service.TypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TypeController implements TypeApi {
    private final TypeService typeService;

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @Override
    public ResponseEntity<?> getTypes() {
        return typeService.getTypes();
    }

    @Override
    public ResponseEntity<?> createType(TypeDto typeDto) {
        return typeService.createType(typeDto);
    }

    @Override
    public ResponseEntity<?> updateType(TypeDto typeDto) {
        return typeService.updateType(typeDto);
    }

    @Override
    public ResponseEntity<?> deleteType(Long id) {
        return typeService.deleteType(id);
    }
}
