package com.example.migrations.controllers.api_impl;

import com.example.migrations.controllers.TypeApi;
import com.example.migrations.dto.TypeCreateDto;
import com.example.migrations.dto.TypeRsDto;
import com.example.migrations.dto.TypeUpdateDto;
import com.example.migrations.service.TypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class TypeController implements TypeApi {
    private final TypeService typeService;

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @Override
    public ResponseEntity<List<TypeRsDto>> getTypes() {
        List<TypeRsDto> types = typeService.getTypes();
        return ResponseEntity.ok(types);
    }

    @Override
    public ResponseEntity<TypeRsDto> createType(TypeCreateDto typeCreateDto) {
        TypeRsDto createdType = typeService.createType(typeCreateDto);
        return ResponseEntity.ok(createdType);
    }

    @Override
    public ResponseEntity<TypeRsDto> updateType(Long id, TypeUpdateDto typeUpdateDto) {
            TypeRsDto updatedType = typeService.updateType(id, typeUpdateDto);
            return ResponseEntity.ok(updatedType);
    }

    @Override
    public ResponseEntity<Void> deleteType(Long id) {
            typeService.deleteType(id);
            return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<TypeRsDto> getTypeById(Long id) {
            TypeRsDto type = typeService.getTypeById(id);
            return ResponseEntity.ok(type);
    }
}
