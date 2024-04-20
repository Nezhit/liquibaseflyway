package com.example.migrations.controllers;

import com.example.migrations.controllers.api.GoodApi;
import com.example.migrations.dto.GoodDto;
import com.example.migrations.service.GoodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoodController implements GoodApi {
    private final GoodService goodService;

    public GoodController(GoodService goodService) {
        this.goodService = goodService;
    }

    @Override
    public ResponseEntity<?> getGoods() {
        return goodService.getGoods();
    }

    @Override
    public ResponseEntity<?> createGood(GoodDto goodDto) {
        return goodService.createGood(goodDto);
    }

    @Override
    public ResponseEntity<?> updateGood(GoodDto goodDto) {
        return goodService.updateGood(goodDto);
    }

    @Override
    public ResponseEntity<?> deleteGood(Long id) {
        return goodService.deleteGood(id);
    }
}
