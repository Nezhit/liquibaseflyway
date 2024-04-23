package com.example.migrations.service;

import com.example.migrations.dto.GoodDto;
import com.example.migrations.entity.Good;
import com.example.migrations.repository.GoodRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodService {
    private final GoodRepo goodRepo;

    public GoodService(GoodRepo goodRepo) {
        this.goodRepo = goodRepo;
    }

    public List<Good> getGoods() {
        return goodRepo.findAll();
    }

    public Good createGood(GoodDto goodDto) {
        Good good= new Good();
        good.setProducer(goodDto.getProducer());
        good.setTitle(goodDto.getTitle());
        good.setType(goodDto.getType());
        goodRepo.save(good);
        return good;
    }

    public Good updateGood(Long id, GoodDto goodDto) {
        Good good=goodRepo.findById(id).get();
        good.setProducer(goodDto.getProducer());
        good.setTitle(goodDto.getTitle());
        good.setType(goodDto.getType());
        goodRepo.save(good);
        return good;

    }

    public Good deleteGood(Long id) {
        Good good=goodRepo.findById(id).get();
        goodRepo.delete(good);
        return good;
    }
}
