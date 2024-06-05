package com.example.migrations.controllers;

import com.example.migrations.dto.GoodCreateDto;
import com.example.migrations.dto.GoodRsDto;
import com.example.migrations.dto.GoodUpdateDto;
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

@Tag(name = "Good API", description = "API for good management")
@RequestMapping("/api/good")
public interface GoodApi {
    @GetMapping
    @Operation(
            summary = "Get goods",
            method = "GET",
            tags = {"Good API"},
            description = "Retrieve a list of all goods",
            operationId = "getGoods"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All goods retrieved successfully",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GoodRsDto.class)))}),
            @ApiResponse(responseCode = "500", description = "Internal server error")

    })
    public ResponseEntity<List<GoodRsDto>> getGoods();

    @PostMapping
    @Operation(
            summary = "Create good",
            method = "POST",
            tags = {"Good API"},
            description = "Create a new good using the provided DTO",
            operationId = "createGood"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Good created successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GoodRsDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid data provided"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<GoodRsDto> createGood(@Schema(implementation = GoodCreateDto.class) @RequestBody GoodCreateDto goodCreateDto);

    @PutMapping("/{id}")
    @Operation(
            summary = "Update good",
            method = "PUT",
            tags = {"Good API"},
            description = "Update an existing good by ID using the provided DTO",
            operationId = "updateGood",
            parameters = @Parameter(name = "id", description = "ID of the good to update", required = true, example = "1")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Good updated successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GoodRsDto.class))}),
            @ApiResponse(responseCode = "404", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Good not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<GoodRsDto> updateGood(@PathVariable Long id, @Schema(implementation = GoodUpdateDto.class) @RequestBody GoodUpdateDto goodUpdateDto);

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete good",
            method = "DELETE",
            tags = {"Good API"},
            description = "Delete a good by ID",
            operationId = "deleteGood",
            parameters = @Parameter(name = "id", description = "ID of the good to delete", required = true, example = "1")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Good deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Good not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteGood(@PathVariable Long id);

    @GetMapping("/{id}")
    @Operation(
            summary = "Get good by id",
            method = "GET",
            tags = {"Good API"},
            description = "Retrieve a good by its ID",
            operationId = "getGoodById",
            parameters = @Parameter(name = "id", description = "ID of the good to get", required = true, example = "1")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Good found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GoodRsDto.class))}),
            @ApiResponse(responseCode = "404", description = "Good not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<GoodRsDto> getGoodById(@PathVariable Long id);
}

