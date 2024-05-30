package com.example.migrations.controllers.api_impl;

import com.example.migrations.controllers.GoodApi;
import com.example.migrations.dto.GoodCreateDto;
import com.example.migrations.dto.GoodRsDto;
import com.example.migrations.dto.GoodUpdateDto;
import com.example.migrations.service.GoodService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class GoodController implements GoodApi {
    private final GoodService goodService;

    public GoodController(GoodService goodService) {
        this.goodService = goodService;
    }

    @Override
    public List<GoodRsDto> getGoods() {
        return goodService.getGoods();
    }

    @Override
    public GoodRsDto createGood(GoodCreateDto goodCreateDto) {
        return goodService.createGood(goodCreateDto);
    }

    @Override
    public GoodRsDto updateGood(Long id, GoodUpdateDto goodUpdateDto) {
        return goodService.updateGood(id, goodUpdateDto);
    }

    @Override
    public void deleteGood(Long id) {
        goodService.deleteGood(id);
    }

    @Override
    public GoodRsDto getGoodById(Long id) {
        return goodService.getGoodById(id);
    }
}
