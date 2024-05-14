package com.example.migrations.controllers;

import com.example.migrations.dto.EmployeeUpdateDto;
import com.example.migrations.dto.ProducerCreateDto;
import com.example.migrations.dto.ProducerRsDto;
import com.example.migrations.dto.ProducerUpdateDto;
import com.example.migrations.entity.Producer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@RequestMapping("/api/producer")
@Tag(name = "Producer API", description = "API for producer management")
public interface ProducerApi {
    @GetMapping
    @Operation(summary = "Get producers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all producers"),
            @ApiResponse(responseCode = "404", description = "Producers not found or exception")
    })
    public List<ProducerRsDto> getProducers();

    @PostMapping
    @Operation(summary = "Create producer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producer successfully created"),
            @ApiResponse(responseCode = "404", description = "Producer not created")
    })
    public ProducerRsDto createProducer(@Schema(implementation = ProducerCreateDto.class)@RequestBody ProducerCreateDto producerCreateDto);

    @PutMapping("/{id}")
    @Operation(summary = "Update producer",
            parameters = @Parameter(name = "id", description = "ID of the customer to update", required = true, example = "1"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producer successfully updated"),
            @ApiResponse(responseCode = "404", description = "Producer not updated")
    })
    public ProducerRsDto updateProducer(@PathVariable Long id, @Schema(implementation = ProducerUpdateDto.class)@RequestBody ProducerUpdateDto producerUpdateDto);

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete producer",
            parameters = @Parameter(name = "id", description = "ID of the customer to delete", required = true, example = "1"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producer successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Producer not deleted")
    })
    public void deleteProducer(@PathVariable Long id);

    @GetMapping("/{id}")
    @Operation(summary = "Get producer by id",
            parameters = @Parameter(name = "id", description = "ID of the customer to get", required = true, example = "1"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producer found"),
            @ApiResponse(responseCode = "404", description = "Producer not found or exception")
    })
    public ProducerRsDto getProducerById(@PathVariable Long id);
}

