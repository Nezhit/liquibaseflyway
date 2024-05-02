package com.example.migrations.controllers.api_impl;

import com.example.migrations.controllers.ProducerApi;
import com.example.migrations.dto.ProducerDto;
import com.example.migrations.entity.Producer;
import com.example.migrations.service.ProducerService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
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
    public Producer updateProducer(Long id, ProducerDto producerDto) {
        return producerService.updateProducer(producerDto);
    }

    @Override
    public void deleteProducer(Long id) {
        producerService.deleteProducer(id);
    }

    @Override
    public Producer getProducerById(Long id) {
        return producerService.getProducerById(id);
    }
}
