package com.example.migrations.controllers.api;

import com.example.migrations.dto.ProducerDto;
import com.example.migrations.dto.TypeDto;
import com.example.migrations.entity.Type;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/type")
public interface TypeApi {
    @GetMapping
    public List<Type> getTypes();
    @PostMapping
    public Type createType(@RequestBody TypeDto typeDto);
    @PutMapping("/{id}")
    public Type updateType(@PathVariable Long id ,@RequestBody  TypeDto typeDto);
    @DeleteMapping("/{id}")
    public Type deleteType(@PathVariable Long id);
}
