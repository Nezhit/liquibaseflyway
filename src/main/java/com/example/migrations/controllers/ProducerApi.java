package com.example.migrations.controllers;

import com.example.migrations.dto.ProducerCreateDto;
import com.example.migrations.dto.ProducerRsDto;
import com.example.migrations.dto.ProducerUpdateDto;
import com.example.migrations.entity.Producer;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@RequestMapping("/api/producer")
public interface ProducerApi {
    @GetMapping
    public List<ProducerRsDto> getProducers();

    @PostMapping
    public ProducerRsDto createProducer(@RequestBody ProducerCreateDto producerCreateDto);

    @PutMapping("/{id}")
    public ProducerRsDto updateProducer(@PathVariable Long id, @RequestBody ProducerUpdateDto producerUpdateDto);

    @DeleteMapping("/{id}")
    public void deleteProducer(@PathVariable Long id);

    @GetMapping("/{id}")
    public ProducerRsDto getProducerById(@PathVariable Long id);
}
