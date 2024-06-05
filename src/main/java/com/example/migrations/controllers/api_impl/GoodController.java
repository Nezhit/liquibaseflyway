package com.example.migrations.controllers.api_impl;

import com.example.migrations.controllers.GoodApi;
import com.example.migrations.dto.GoodCreateDto;
import com.example.migrations.dto.GoodRsDto;
import com.example.migrations.dto.GoodUpdateDto;
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
    public ResponseEntity<List<GoodRsDto>> getGoods() {
        List<GoodRsDto> goods = goodService.getGoods();
        return ResponseEntity.ok(goods);
    }

    @Override
    public ResponseEntity<GoodRsDto> createGood(GoodCreateDto goodCreateDto) {
        GoodRsDto createdGood = goodService.createGood(goodCreateDto);
        return ResponseEntity.ok(createdGood);
    }
    @Override
    public ResponseEntity<GoodRsDto> updateGood(Long id, GoodUpdateDto goodUpdateDto) {
            GoodRsDto updatedGood = goodService.updateGood(id, goodUpdateDto);
            return ResponseEntity.ok(updatedGood);
    }

    @Override
    public ResponseEntity<Void> deleteGood(Long id) {
            goodService.deleteGood(id);
            return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<GoodRsDto> getGoodById(Long id) {
            GoodRsDto good = goodService.getGoodById(id);
            return ResponseEntity.ok(good);
    }
}
