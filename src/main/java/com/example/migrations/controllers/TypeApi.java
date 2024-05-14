package com.example.migrations.controllers;

import com.example.migrations.dto.EmployeeUpdateDto;
import com.example.migrations.dto.TypeCreateDto;
import com.example.migrations.dto.TypeRsDto;
import com.example.migrations.dto.TypeUpdateDto;
import com.example.migrations.entity.Type;
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

@RequestMapping("/api/type")
@Tag(name = "Type API", description = "API for type management")
public interface TypeApi {
    @GetMapping
    @Operation(summary = "Get types")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all types"),
            @ApiResponse(responseCode = "404", description = "Types not found or exception")
    })
    public List<TypeRsDto> getTypes();

    @PostMapping
    @Operation(summary = "Create type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Type successfully created"),
            @ApiResponse(responseCode = "404", description = "Type not created")
    })
    public TypeRsDto createType(@Schema(implementation = TypeCreateDto.class)@RequestBody TypeCreateDto typeCreateDto);

    @PutMapping("/{id}")
    @Operation(summary = "Update type",
            parameters = @Parameter(name = "id", description = "ID of the customer to update", required = true, example = "1"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Type successfully updated"),
            @ApiResponse(responseCode = "404", description = "Type not updated")
    })
    public TypeRsDto updateType(@PathVariable Long id, @Schema(implementation = TypeUpdateDto.class)@RequestBody TypeUpdateDto typeUpdateDto);

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete type",
            parameters = @Parameter(name = "id", description = "ID of the customer to delete", required = true, example = "1"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Type successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Type not deleted")
    })
    public void deleteType(@PathVariable Long id);

    @GetMapping("/{id}")
    @Operation(summary = "Get type by id",
            parameters = @Parameter(name = "id", description = "ID of the customer to get", required = true, example = "1"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Type found"),
            @ApiResponse(responseCode = "404", description = "Type not found or exception")
    })
    public TypeRsDto getTypeById(@PathVariable Long id);
}
