package com.example.migrations.controllers;

import com.example.migrations.dto.ProducerCreateDto;
import com.example.migrations.dto.ProducerRsDto;
import com.example.migrations.dto.ProducerUpdateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Tag(name = "Producer API", description = "API for producer management")
@RequestMapping("/api/producer")
public interface ProducerApi {
    @GetMapping
    @Operation(
            summary = "Get producers",
            method = "GET",
            tags = {"Producer API"},
            description = "Retrieve a list of all producers",
            operationId = "getProducers"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All producers retrieved successfully",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProducerRsDto.class)))}),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<ProducerRsDto>> getProducers();

    @PostMapping
    @Operation(
            summary = "Create producer",
            method = "POST",
            tags = {"Producer API"},
            description = "Create a new producer using the provided DTO",
            operationId = "createProducer"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producer created successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProducerRsDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid data provided"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<ProducerRsDto> createProducer(@Schema(implementation = ProducerCreateDto.class) @RequestBody ProducerCreateDto producerCreateDto);

    @PutMapping("/{id}")
    @Operation(
            summary = "Update producer",
            method = "PUT",
            tags = {"Producer API"},
            description = "Update an existing producer by ID using the provided DTO",
            operationId = "updateProducer",
            parameters = @Parameter(name = "id", description = "ID of the producer to update", required = true, example = "1")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producer updated successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProducerRsDto.class))}),
            @ApiResponse(responseCode = "404", description = "Producer not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<ProducerRsDto> updateProducer(@PathVariable Long id, @Schema(implementation = ProducerUpdateDto.class) @RequestBody ProducerUpdateDto producerUpdateDto);

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete producer",
            method = "DELETE",
            tags = {"Producer API"},
            description = "Delete a producer by ID",
            operationId = "deleteProducer",
            parameters = @Parameter(name = "id", description = "ID of the producer to delete", required = true, example = "1")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producer deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Producer not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteProducer(@PathVariable Long id);

    @GetMapping("/{id}")
    @Operation(
            summary = "Get producer by id",
            method = "GET",
            tags = {"Producer API"},
            description = "Retrieve a producer by its ID",
            operationId = "getProducerById",
            parameters = @Parameter(name = "id", description = "ID of the producer to get", required = true, example = "1")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producer found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProducerRsDto.class))}),
            @ApiResponse(responseCode = "404", description = "Producer not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<ProducerRsDto> getProducerById(@PathVariable Long id);
}

