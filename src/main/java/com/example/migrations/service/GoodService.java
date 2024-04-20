package com.example.migrations.service;

import com.example.migrations.dto.GoodDto;
import com.example.migrations.entity.Good;
import com.example.migrations.repository.GoodRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GoodService {
    private final GoodRepo goodRepo;

    public GoodService(GoodRepo goodRepo) {
        this.goodRepo = goodRepo;
    }

    public ResponseEntity<?> getGoods() {
        return ResponseEntity.ok(goodRepo.findAll());
    }

    public ResponseEntity<?> createGood(GoodDto goodDto) {
        Good good= new Good();
        good.setProducer(goodDto.getProducer());
        good.setTitle(goodDto.getTitle());
        good.setType(goodDto.getType());
        goodRepo.save(good);
        return ResponseEntity.ok("Товар создан");
    }

    public ResponseEntity<?> updateGood(GoodDto goodDto) {
        Good good=goodRepo.findById(goodDto.getId()).get();
        good.setProducer(goodDto.getProducer());
        good.setTitle(goodDto.getTitle());
        good.setType(goodDto.getType());
        goodRepo.save(good);
        return ResponseEntity.ok("Товар обновлен");

    }

    public ResponseEntity<?> deleteGood(Long id) {
        Good good=goodRepo.findById(id).get();
        goodRepo.delete(good);
        return ResponseEntity.ok("Товар удаелн");
    }
}
