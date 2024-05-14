package com.example.migrations.controllers;

import com.example.migrations.dto.EmployeeRsDto;
import com.example.migrations.dto.EmployeeUpdateDto;
import com.example.migrations.dto.GoodCreateDto;
import com.example.migrations.dto.GoodRsDto;
import com.example.migrations.dto.GoodUpdateDto;
import com.example.migrations.entity.Good;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
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

@RequestMapping("/api/good")
@Tag(name = "Good API", description = "API for good management")
public interface GoodApi {
    @GetMapping
    @Operation(summary = "Get goods")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all goods", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = List.class))}),
            @ApiResponse(responseCode = "404", description = "Goods not found or exception")
    })
    public List<GoodRsDto> getGoods();

    @PostMapping
    @Operation(summary = "Create good")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Good successfully created", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = GoodRsDto.class))}),
            @ApiResponse(responseCode = "404", description = "Good not created")
    })
    public GoodRsDto createGood(@Schema(implementation = GoodCreateDto.class)@RequestBody GoodCreateDto goodCreateDto);

    @PutMapping("/{id}")
    @Operation(summary = "Update good",
            parameters = @Parameter(name = "id", description = "ID of the customer to update", required = true, example = "1"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Good successfully updated", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = GoodRsDto.class))}),
            @ApiResponse(responseCode = "404", description = "Good not updated")
    })
    public GoodRsDto updateGood(@PathVariable Long id, @Schema(implementation = GoodUpdateDto.class)@RequestBody GoodUpdateDto goodUpdateDto);

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete good",
            parameters = @Parameter(name = "id", description = "ID of the customer to delete", required = true, example = "1"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Good successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Good not deleted")
    })
    public void deleteGood(@PathVariable Long id);

    @GetMapping("/{id}")
    @Operation(summary = "Get good by id",
            parameters = @Parameter(name = "id", description = "ID of the customer to get", required = true, example = "1"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Good found", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = GoodRsDto.class))}),
            @ApiResponse(responseCode = "404", description = "Good not found or exception")
    })
    public GoodRsDto getGoodById(@PathVariable Long id);
}

