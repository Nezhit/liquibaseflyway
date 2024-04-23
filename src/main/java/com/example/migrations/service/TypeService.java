package com.example.migrations.service;

import com.example.migrations.dto.TypeDto;
import com.example.migrations.entity.Type;
import com.example.migrations.repository.TypeRepo;
import org.springframework.http.ResponseEntity;
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

    public Type createType(TypeDto typeDto) {
        Type type=new Type();
        type.setTitle(typeDto.getTitle());
        typeRepo.save(type);
        return type;
    }

    public Type updateType(Long id,TypeDto typeDto) {
        Type type=typeRepo.findById(id).get();
        type.setTitle(typeDto.getTitle());
        typeRepo.save(type);
        return type;

    }

    public Type deleteType(Long id) {
        Type type=typeRepo.findById(id).get();
        typeRepo.delete(type);
        return type;
    }
}
