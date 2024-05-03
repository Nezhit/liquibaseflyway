package com.example.migrations.controllers;

import com.example.migrations.dto.ProducerCreateDto;
import com.example.migrations.dto.ProducerRsDto;
import com.example.migrations.dto.ProducerUpdateDto;
import com.example.migrations.entity.Producer;
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

@RequestMapping("/api/producer")
public interface ProducerApi {
    @GetMapping
    @Operation(summary = "Get producers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all producers"),
            @ApiResponse(responseCode = "404", description = "Producers not found or exception")
    })
    public List<ProducerRsDto> getProducers();

    @PostMapping
    @Operation(summary = "Create producers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producer created"),
            @ApiResponse(responseCode = "404", description = "Producer not created")
    })
    public ProducerRsDto createProducer(@RequestBody ProducerCreateDto producerCreateDto);

    @PutMapping("/{id}")
    @Operation(summary = "Update producers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producer updated"),
            @ApiResponse(responseCode = "404", description = "Producer not updated")
    })
    public ProducerRsDto updateProducer(@PathVariable Long id, @RequestBody ProducerUpdateDto producerUpdateDto);

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete producer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producer deleted"),
            @ApiResponse(responseCode = "404", description = "Producers not deleted")
    })
    public void deleteProducer(@PathVariable Long id);

    @GetMapping("/{id}")
    @Operation(summary = "Get producer by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producer found"),
            @ApiResponse(responseCode = "404", description = "Producers not found or exception")
    })
    public ProducerRsDto getProducerById(@PathVariable Long id);
}
