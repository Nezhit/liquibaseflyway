package com.example.migrations.controllers.api;

import com.example.migrations.dto.EmployeeDto;
import com.example.migrations.dto.GoodDto;
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
public interface GoodApi {
    @GetMapping("/api/getGoods")
    @Operation(summary = "Get goods")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get all goods"),
            @ApiResponse(responseCode = "404", description = "Goods not found or exception")
    })
    public ResponseEntity<?> getGoods();
    @PostMapping("/api/createGood")
    @Operation(summary = "Create goods")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Good created"),
            @ApiResponse(responseCode = "404", description = "Good  not created")
    })
    public ResponseEntity<?>createGood(@RequestBody GoodDto goodDto);
    @PutMapping("/api/updateGood")
    @Operation(summary = "Update goods")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Good updated"),
            @ApiResponse(responseCode = "404", description = "Good  not updated")
    })
    public ResponseEntity<?> updateGood(@RequestBody GoodDto goodDto);
    @DeleteMapping("/api/deleteGood/{id}")
    @Operation(summary = "Create goods")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Good deleted"),
            @ApiResponse(responseCode = "404", description = "Good  not deleted")
    })
    public ResponseEntity<?> deleteGood(@PathVariable Long id);
}
