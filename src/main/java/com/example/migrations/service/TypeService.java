package com.example.migrations.service;

import com.example.migrations.dto.TypeCreateDto;
import com.example.migrations.dto.TypeRsDto;
import com.example.migrations.dto.TypeUpdateDto;
import com.example.migrations.entity.Type;
import com.example.migrations.repository.TypeRepo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TypeService {
    private final TypeRepo typeRepo;

    public TypeService(TypeRepo typeRepo) {
        this.typeRepo = typeRepo;
    }

    public List<Type> getTypes() {
        return typeRepo.findAll();
    }

    public TypeRsDto createType(TypeCreateDto typeCreateDto) {
        Type type = new Type(typeCreateDto);
        return new TypeRsDto(typeRepo.save(type));
    }

    public TypeRsDto updateType(Long id, TypeUpdateDto typeUpdateDto) {
        Type type = typeRepo.findById(id).orElseThrow(() -> new RuntimeException("Тип не найден"));
        if (typeUpdateDto.getTitle() != null) type.setTitle(typeUpdateDto.getTitle());
        return new TypeRsDto(typeRepo.save(type));
    }

    public void deleteType(Long id) {
        Type type = typeRepo.findById(id).orElseThrow(() -> new RuntimeException("Тип не найден"));
        typeRepo.delete(type);
    }

    public TypeRsDto getTypeById(Long id) {
        return new TypeRsDto(typeRepo.findById(id).orElseThrow(() -> new RuntimeException("Тип не найден")));
    }
}
