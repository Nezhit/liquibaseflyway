package com.example.migrations.service;

import com.example.migrations.dto.TypeDto;
import com.example.migrations.entity.Type;
import com.example.migrations.repository.TypeRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TypeService {
    private final TypeRepo typeRepo;

    public TypeService(TypeRepo typeRepo) {
        this.typeRepo = typeRepo;
    }

    public ResponseEntity<?> getTypes() {
        return ResponseEntity.ok(typeRepo.findAll());
    }

    public ResponseEntity<?> createType(TypeDto typeDto) {
        Type type=new Type();
        type.setTitle(typeDto.getTitle());
        typeRepo.save(type);
        return ResponseEntity.ok("Тип создан");
    }

    public ResponseEntity<?> updateType(TypeDto typeDto) {
        Type type=typeRepo.findById(typeDto.getId()).get();
        type.setTitle(typeDto.getTitle());
        typeRepo.save(type);
        return ResponseEntity.ok("Тип обновлен");

    }

    public ResponseEntity<?> deleteType(Long id) {
        Type type=typeRepo.findById(id).get();
        typeRepo.delete(type);
        return ResponseEntity.ok("Удален тип");
    }
}
