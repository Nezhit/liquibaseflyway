package com.example.migrations.service;

import com.example.migrations.dto.GoodCreateDto;
import com.example.migrations.dto.GoodRsDto;
import com.example.migrations.dto.GoodUpdateDto;
import com.example.migrations.entity.Good;
import com.example.migrations.repository.GoodRepo;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Tag(name = "Good service", description = "Service providing basci CRUD operations")
public class GoodService {
    private final GoodRepo goodRepo;

    public GoodService(GoodRepo goodRepo) {
        this.goodRepo = goodRepo;
    }

    public List<GoodRsDto> getGoods() {
        return goodRepo.findAll().stream().map(GoodRsDto::new).collect(Collectors.toList());
    }

    public GoodRsDto createGood(GoodCreateDto goodCreateDto) {
        Good good = new Good(goodCreateDto);
        return new GoodRsDto(goodRepo.save(good));
    }

    public GoodRsDto updateGood(Long id, GoodUpdateDto goodUpdateDto) {
        Good good = goodRepo.findById(id).orElseThrow(() -> new RuntimeException("Товар не найден"));
        if (goodUpdateDto.getProducer() != null) good.setProducer(goodUpdateDto.getProducer());
        if (goodUpdateDto.getTitle() != null) good.setTitle(goodUpdateDto.getTitle());
        if (goodUpdateDto.getType() != null) good.setType(goodUpdateDto.getType());
        return new GoodRsDto(goodRepo.save(good));
    }

    public void deleteGood(Long id) {
        Good good = goodRepo.findById(id).orElseThrow(() -> new RuntimeException("Товар не найден"));
        goodRepo.delete(good);
    }

    public GoodRsDto getGoodById(Long id) {
        return new GoodRsDto(goodRepo.findById(id).orElseThrow(() -> new RuntimeException("Товар не найден")));
    }
}
