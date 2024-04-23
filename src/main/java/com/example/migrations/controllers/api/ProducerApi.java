package com.example.migrations.controllers.api;

import com.example.migrations.dto.EmployeeDto;
import com.example.migrations.dto.ProducerDto;
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
    public ResponseEntity<?> getProducers();
    @PostMapping("/api/createProducer")
    public ResponseEntity<?>createProducer(@RequestBody ProducerDto producerDto);
    @PutMapping("/api/updateProducer")
    public ResponseEntity<?> updateProducer(@RequestBody  ProducerDto producerDto);
    @DeleteMapping("/api/deleteProducer/{id}")
    public ResponseEntity<?> deleteProducer(@PathVariable Long id);
}
