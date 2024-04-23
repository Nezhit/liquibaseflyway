package com.example.migrations.controllers;

import com.example.migrations.controllers.api.ProducerApi;
import com.example.migrations.dto.ProducerDto;
import com.example.migrations.entity.Producer;
import com.example.migrations.repository.ProducerRepo;
import com.example.migrations.service.ProducerService;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ProducerController implements ProducerApi {
    private final ProducerService producerService;

    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }


    @Override
    public List<Producer> getProducers() {
        return producerService.getProducers();
    }

    @Override
    public Producer createProducer(ProducerDto producerDto) {
        return producerService.createProducer(producerDto);
    }

    @Override
    public Producer updateProducer(Long id,ProducerDto producerDto) {
        return producerService.updateProducer(producerDto);
    }

    @Override
    public Producer deleteProducer(Long id) {
        return producerService.deleteProducer(id);
    }
}
