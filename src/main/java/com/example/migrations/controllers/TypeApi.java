package com.example.migrations.controllers;

import com.example.migrations.dto.TypeCreateDto;
import com.example.migrations.dto.TypeRsDto;
import com.example.migrations.dto.TypeUpdateDto;
import com.example.migrations.entity.Type;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@RequestMapping("/api/type")
public interface TypeApi {
    @GetMapping
    public List<TypeRsDto> getTypes();

    @PostMapping
    public TypeRsDto createType(@Valid @RequestBody TypeCreateDto typeCreateDto);

    @PutMapping("/{id}")
    public TypeRsDto updateType(@PathVariable Long id, @RequestBody TypeUpdateDto typeUpdateDto);

    @DeleteMapping("/{id}")
    public void deleteType(@PathVariable Long id);

    @GetMapping("/{id}")
    public TypeRsDto getTypeById(@PathVariable Long id);
}
