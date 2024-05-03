package com.example.migrations.controllers;

import com.example.migrations.dto.GoodCreateDto;
import com.example.migrations.dto.GoodRsDto;
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
    public List<GoodRsDto> getGoods();

    @PostMapping
    public GoodRsDto createGood(@RequestBody GoodCreateDto goodCreateDto);

    @PutMapping("/{id}")
    public GoodRsDto updateGood(@PathVariable Long id, @RequestBody GoodUpdateDto goodUpdateDto);

    @DeleteMapping("/{id}")
    public void deleteGood(@PathVariable Long id);

    @GetMapping("/{id}")
    public GoodRsDto getGoodById(@PathVariable Long id);
}
