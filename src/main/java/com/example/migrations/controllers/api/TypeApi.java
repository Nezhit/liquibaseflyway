package com.example.migrations.controllers.api;

import com.example.migrations.dto.ProducerDto;
import com.example.migrations.dto.TypeDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface TypeApi {
    @GetMapping("/api/getTypes")
    @Operation(summary = "Get types")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Found types"),
            @ApiResponse(responseCode = "404", description = "Types not found or exception")
    })
    public ResponseEntity<?> getTypes();
    @PostMapping("/api/createType")
    @Operation(summary = "Create types")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Type created"),
            @ApiResponse(responseCode = "404", description = "Type not created")
    })
    public ResponseEntity<?>createType(@RequestBody TypeDto typeDto);
    @PutMapping("/api/updateType")
    @Operation(summary = "Update types")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Type updated"),
            @ApiResponse(responseCode = "404", description = "Type not updated")
    })
    public ResponseEntity<?> updateType(@RequestBody  TypeDto typeDto);
    @DeleteMapping("/api/deleteType/{id}")
    @Operation(summary = "Delete types")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Type deleted"),
            @ApiResponse(responseCode = "404", description = "Type not deleted")
    })
    public ResponseEntity<?> deleteType(@PathVariable Long id);
}
