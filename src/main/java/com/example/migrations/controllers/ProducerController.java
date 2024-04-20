package com.example.migrations.controllers;

import com.example.migrations.controllers.api.ProducerApi;
import com.example.migrations.dto.ProducerDto;
import com.example.migrations.repository.ProducerRepo;
import com.example.migrations.service.ProducerService;
import org.springframework.http.ResponseEntity;

public class ProducerController implements ProducerApi {
    private final ProducerService producerService;
    private final ProducerRepo producerRepo;

    public ProducerController(ProducerService producerService, ProducerRepo producerRepo) {
        this.producerService = producerService;
        this.producerRepo = producerRepo;
    }

    @Override
    public ResponseEntity<?> getProducers() {
        return producerService.getProducers();
    }

    @Override
    public ResponseEntity<?> createProducer(ProducerDto producerDto) {
        return producerService.createProducer(producerDto);
    }

    @Override
    public ResponseEntity<?> updateProducer(ProducerDto producerDto) {
        return producerService.updateProducer(producerDto);
    }

    @Override
    public ResponseEntity<?> deleteProducer(Long id) {
        return producerService.deleteProducer(id);
    }
}
