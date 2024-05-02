package com.example.migrations.service;

import com.example.migrations.dto.GoodDto;
import com.example.migrations.dto.GoodUpdateDto;
import com.example.migrations.entity.Good;
import com.example.migrations.repository.GoodRepo;
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
        Good good = new Good(goodDto);
        return goodRepo.save(good);
    }

    public Good updateGood(Long id, GoodUpdateDto goodUpdateDto) {
        Good good = goodRepo.findById(id).get();
        if(goodUpdateDto.getProducer()!=null) good.setProducer(goodUpdateDto.getProducer());
        if(goodUpdateDto.getTitle()!=null) good.setTitle(goodUpdateDto.getTitle());
        if(goodUpdateDto.getType()!=null) good.setType(goodUpdateDto.getType());
        return goodRepo.save(good);
    }

    public void deleteGood(Long id) {
        Good good = goodRepo.findById(id).get();
        goodRepo.delete(good);
    }

    public Good getGoodById(Long id) {
        return goodRepo.findById(id).get();
    }
}
