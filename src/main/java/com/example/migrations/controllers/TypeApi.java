package com.example.migrations.controllers;

import com.example.migrations.dto.TypeCreateDto;
import com.example.migrations.dto.TypeRsDto;
import com.example.migrations.dto.TypeUpdateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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

@Tag(name = "Type API", description = "API for type management")
@RequestMapping("/api/type")
public interface TypeApi {
    @GetMapping
    @Operation(
            summary = "Get types",
            method = "GET",
            tags = {"Type API"},
            description = "Retrieve a list of all types.",
            operationId = "getTypes"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all types", content =
                    {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = TypeRsDto.class)))}),
            @ApiResponse(responseCode = "500", description = "Types not found or exception")
    })
    public List<TypeRsDto> getTypes();

    @PostMapping
    @Operation(
            summary = "Create type",
            method = "POST",
            tags = {"Type API"},
            description = "Create a new type using the provided DTO.",
            operationId = "createType"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Type successfully created"),
            @ApiResponse(responseCode = "500", description = "Type not created")
    })
    public TypeRsDto createType(@Schema(implementation = TypeCreateDto.class) @RequestBody TypeCreateDto typeCreateDto);

    @PutMapping("/{id}")
    @Operation(
            summary = "Update type",
            method = "PUT",
            tags = {"Type API"},
            description = "Update an existing type by ID using the provided DTO.",
            operationId = "updateType",
            parameters = @Parameter(name = "id", description = "ID of the type to update", required = true, example = "1")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Type successfully updated"),
            @ApiResponse(responseCode = "500", description = "Type not updated")
    })
    public TypeRsDto updateType(@PathVariable Long id, @Schema(implementation = TypeUpdateDto.class) @RequestBody TypeUpdateDto typeUpdateDto);

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete type",
            method = "DELETE",
            tags = {"Type API"},
            description = "Delete a type by ID.",
            operationId = "deleteType",
            parameters = @Parameter(name = "id", description = "ID of the type to delete", required = true, example = "1")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Type successfully deleted"),
            @ApiResponse(responseCode = "500", description = "Type not deleted")
    })
    public void deleteType(@PathVariable Long id);

    @GetMapping("/{id}")
    @Operation(
            summary = "Get type by id",
            method = "GET",
            tags = {"Type API"},
            description = "Retrieve a type by its ID.",
            operationId = "getTypeById",
            parameters = @Parameter(name = "id", description = "ID of the type to get", required = true, example = "1")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Type found"),
            @ApiResponse(responseCode = "500", description = "Type not found or exception")
    })
    public TypeRsDto getTypeById(@PathVariable Long id);
}
