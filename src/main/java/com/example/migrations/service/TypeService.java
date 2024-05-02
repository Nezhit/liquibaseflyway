package com.example.migrations.service;

import com.example.migrations.dto.TypeDto;
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

    public Type createType(TypeDto typeDto) {
        Type type = new Type(typeDto);
        return typeRepo.save(type);
    }

    public Type updateType(Long id, TypeUpdateDto typeUpdateDto) {
        Type type = typeRepo.findById(id).get();
        if(typeUpdateDto.getTitle()!=null) type.setTitle(typeUpdateDto.getTitle());
        return typeRepo.save(type);
    }

    public void deleteType(Long id) {
        Type type = typeRepo.findById(id).get();
        typeRepo.delete(type);
    }

    public Type getTypeById(Long id) {
        return typeRepo.findById(id).get();
    }
}
