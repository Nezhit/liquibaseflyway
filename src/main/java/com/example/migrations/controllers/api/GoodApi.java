package com.example.migrations.controllers.api;

import com.example.migrations.dto.EmployeeDto;
import com.example.migrations.dto.GoodDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface GoodApi {
    @GetMapping("/api/getGoods")
    public ResponseEntity<?> getGoods();
    @PostMapping("/api/createGood")
    public ResponseEntity<?>createGood(@RequestBody GoodDto goodDto);
    @PutMapping("/api/updateGood")
    public ResponseEntity<?> updateGood(@RequestBody GoodDto goodDto);
    @DeleteMapping("/api/deleteGood/{id}")
    public ResponseEntity<?> deleteGood(@PathVariable Long id);
}
