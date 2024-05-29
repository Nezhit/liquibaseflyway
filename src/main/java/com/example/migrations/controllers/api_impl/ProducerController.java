package com.example.migrations.controllers.api_impl;

import com.example.migrations.controllers.ProducerApi;
import com.example.migrations.dto.ProducerCreateDto;
import com.example.migrations.dto.ProducerRsDto;
import com.example.migrations.dto.ProducerUpdateDto;
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
    public List<ProducerRsDto> getProducers() {
        return producerService.getProducers();
    }

    @Override
    public ProducerRsDto createProducer(ProducerCreateDto producerCreateDto) {
        return producerService.createProducer(producerCreateDto);
    }

    @Override
    public ProducerRsDto updateProducer(Long id, ProducerUpdateDto producerUpdateDto) {
        return producerService.updateProducer(id,producerUpdateDto);
    }

    @Override
    public void deleteProducer(Long id) {
        producerService.deleteProducer(id);
    }

    @Override
    public ProducerRsDto getProducerById(Long id) {
        return producerService.getProducerById(id);
    }
}
