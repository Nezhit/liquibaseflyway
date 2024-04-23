package com.example.migrations.controllers.api;

import com.example.migrations.dto.ProducerDto;
import com.example.migrations.dto.TypeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface TypeApi {
    @GetMapping("/api/getTypes")
    public ResponseEntity<?> getTypes();
    @PostMapping("/api/createType")
    public ResponseEntity<?>createType(@RequestBody TypeDto typeDto);
    @PutMapping("/api/updateType")
    public ResponseEntity<?> updateType(@RequestBody  TypeDto typeDto);
    @DeleteMapping("/api/deleteType/{id}")
    public ResponseEntity<?> deleteType(@PathVariable Long id);
}
