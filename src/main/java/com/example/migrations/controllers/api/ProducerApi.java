package com.example.migrations.controllers.api;

import com.example.migrations.dto.EmployeeDto;
import com.example.migrations.dto.ProducerDto;
import com.example.migrations.entity.Producer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/producer")
public interface ProducerApi {
    @GetMapping
    public List<Producer> getProducers();
    @PostMapping
    public Producer createProducer(@RequestBody ProducerDto producerDto);
    @PutMapping("/{id}")
    public Producer updateProducer(@PathVariable Long id,@RequestBody  ProducerDto producerDto);
    @DeleteMapping("/{id}")
    public Producer deleteProducer(@PathVariable Long id);
}
