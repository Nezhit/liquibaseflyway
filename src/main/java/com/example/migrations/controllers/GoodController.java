package com.example.migrations.controllers;

import com.example.migrations.controllers.api.GoodApi;
import com.example.migrations.dto.GoodDto;
import com.example.migrations.entity.Good;
import com.example.migrations.service.GoodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GoodController implements GoodApi {
    private final GoodService goodService;

    public GoodController(GoodService goodService) {
        this.goodService = goodService;
    }

    @Override
    public List<Good> getGoods() {
        return goodService.getGoods();
    }

    @Override
    public Good createGood(GoodDto goodDto) {
        return goodService.createGood(goodDto);
    }

    @Override
    public Good updateGood(Long id,GoodDto goodDto) {
        return goodService.updateGood(id,goodDto);
    }

    @Override
    public Good deleteGood(Long id) {
        return goodService.deleteGood(id);
    }
}
