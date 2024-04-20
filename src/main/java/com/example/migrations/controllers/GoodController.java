package com.example.migrations.controllers;

import com.example.migrations.controllers.api.GoodApi;
import com.example.migrations.dto.GoodDto;
import com.example.migrations.service.GoodService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@Tag(name = "Good controller", description = "Implementation of good API")
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
    public ResponseEntity<?> createGood(@Parameter(name = "GoodDTO",
            description = "Data transfer object of a good")
                                            @Valid
                                            GoodDto goodDto) {
        return goodService.createGood(goodDto);
    }

    @Override
    public ResponseEntity<?> updateGood(@Parameter(name = "GoodDTO",
            description = "Data transfer object of a good")
                                            @Valid
                                            GoodDto goodDto) {
        return goodService.updateGood(goodDto);
    }

    @Override
    public ResponseEntity<?> deleteGood(@Parameter(name = "id",
            description = "Identificator of order") Long id) {
        return goodService.deleteGood(id);
    }
}
