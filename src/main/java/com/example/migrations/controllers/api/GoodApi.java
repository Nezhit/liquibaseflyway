package com.example.migrations.controllers.api;

import com.example.migrations.dto.EmployeeDto;
import com.example.migrations.dto.GoodDto;
import com.example.migrations.entity.Good;
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
@RequestMapping("/api/good")
public interface GoodApi {
    @GetMapping
    public List<Good> getGoods();
    @PostMapping
    public Good createGood(@RequestBody GoodDto goodDto);
    @PutMapping("/{id}")
    public Good updateGood(@PathVariable Long id,@RequestBody GoodDto goodDto);
    @DeleteMapping("{id}")
    public Good deleteGood(@PathVariable Long id);
}
