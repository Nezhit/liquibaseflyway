package com.example.migrations.controllers.api;

import com.example.migrations.dto.EmployeeDto;
import com.example.migrations.dto.ProducerDto;
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

public interface ProducerApi {
    @GetMapping("/api/getProducers")
    @Operation(summary = "Get producers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all producers"),
            @ApiResponse(responseCode = "404", description = "Producers not found or exception")
    })
    public ResponseEntity<?> getProducers();
    @PostMapping("/api/createProducer")
    @Operation(summary = "Create producers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producer created"),
            @ApiResponse(responseCode = "404", description = "Producer not created")
    })
    public ResponseEntity<?>createProducer(@RequestBody ProducerDto producerDto);
    @PutMapping("/api/updateProducer")
    @Operation(summary = "Update producers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producer updated"),
            @ApiResponse(responseCode = "404", description = "Producer not updated")
    })
    public ResponseEntity<?> updateProducer(@RequestBody  ProducerDto producerDto);
    @DeleteMapping("/api/deleteProducer/{id}")
    @Operation(summary = "Delete producer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producer deleted"),
            @ApiResponse(responseCode = "404", description = "Producers not deleted")
    })
    public ResponseEntity<?> deleteProducer(@PathVariable Long id);
}
