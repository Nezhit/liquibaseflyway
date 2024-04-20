package com.example.migrations.controllers;

import com.example.migrations.controllers.api.TypeApi;
import com.example.migrations.dto.TypeDto;
import com.example.migrations.service.TypeService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@Tag(name = "Type controller", description = "Implementation of type API")
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
    public ResponseEntity<?> createType(@Parameter(name = "TypeDTO",
            description = "Data transfer object of a type")
                                            @Valid
                                            TypeDto typeDto) {
        return typeService.createType(typeDto);
    }

    @Override
    public ResponseEntity<?> updateType(@Parameter(name = "TypeDTO",
            description = "Data transfer object of a type")
                                            @Valid
                                            TypeDto typeDto) {
        return typeService.updateType(typeDto);
    }

    @Override
    public ResponseEntity<?> deleteType(@Parameter(name = "id",
            description = "Identificator of type")
                                            Long id) {
        return typeService.deleteType(id);
    }
}
