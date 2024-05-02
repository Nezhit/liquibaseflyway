package com.example.migrations.controllers;

import com.example.migrations.dto.GoodDto;
import com.example.migrations.dto.GoodUpdateDto;
import com.example.migrations.entity.Good;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@RequestMapping("/api/good")
public interface GoodApi {
    @GetMapping
    public List<Good> getGoods();

    @PostMapping
    public Good createGood(@RequestBody GoodDto goodDto);

    @PutMapping("/{id}")
    public Good updateGood(@PathVariable Long id, @RequestBody GoodUpdateDto goodUpdateDto);

    @DeleteMapping("/{id}")
    public void deleteGood(@PathVariable Long id);

    @GetMapping("/{id}")
    public Good getGoodById(@PathVariable Long id);
}
