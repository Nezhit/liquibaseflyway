package com.example.migrations.controllers;

import com.example.migrations.dto.GoodCreateDto;
import com.example.migrations.dto.GoodRsDto;
import com.example.migrations.dto.GoodUpdateDto;
import com.example.migrations.entity.Good;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Get goods")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get all goods"),
            @ApiResponse(responseCode = "404", description = "Goods not found or exception")
    })
    public List<GoodRsDto> getGoods();

    @PostMapping
    @Operation(summary = "Create goods")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Good created"),
            @ApiResponse(responseCode = "404", description = "Good  not created")
    })
    public GoodRsDto createGood(@RequestBody GoodCreateDto goodCreateDto);

    @PutMapping("/{id}")
    @Operation(summary = "Update goods")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Good updated"),
            @ApiResponse(responseCode = "404", description = "Good  not updated")
    })
    public GoodRsDto updateGood(@PathVariable Long id, @RequestBody GoodUpdateDto goodUpdateDto);

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete goods")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Good deleted"),
            @ApiResponse(responseCode = "404", description = "Good  not deleted")
    })
    public void deleteGood(@PathVariable Long id);

    @GetMapping("/{id}")
    @Operation(summary = "Get good by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Good found"),
            @ApiResponse(responseCode = "404", description = "Good  not found or exception")
    })
    public GoodRsDto getGoodById(@PathVariable Long id);
}
