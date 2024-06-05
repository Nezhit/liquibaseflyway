package com.example.migrations.controllers.api_impl;

import com.example.migrations.controllers.ProducerApi;
import com.example.migrations.dto.ProducerCreateDto;
import com.example.migrations.dto.ProducerRsDto;
import com.example.migrations.dto.ProducerUpdateDto;
import com.example.migrations.service.ProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ProducerController implements ProducerApi {
    private final ProducerService producerService;

    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }


    @Override
    public ResponseEntity<List<ProducerRsDto>> getProducers() {
        List<ProducerRsDto> producers = producerService.getProducers();
        return ResponseEntity.ok(producers);
    }

    @Override
    public ResponseEntity<ProducerRsDto> createProducer(ProducerCreateDto producerCreateDto) {
        ProducerRsDto createdProducer = producerService.createProducer(producerCreateDto);
        return ResponseEntity.ok(createdProducer);
    }

    @Override
    public ResponseEntity<ProducerRsDto> updateProducer(Long id, ProducerUpdateDto producerUpdateDto) {
            ProducerRsDto updatedProducer = producerService.updateProducer(id, producerUpdateDto);
            return ResponseEntity.ok(updatedProducer);
    }

    @Override
    public ResponseEntity<Void> deleteProducer(Long id) {
            producerService.deleteProducer(id);
            return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<ProducerRsDto> getProducerById(Long id) {
            ProducerRsDto producer = producerService.getProducerById(id);
            return ResponseEntity.ok(producer);
    }
}
