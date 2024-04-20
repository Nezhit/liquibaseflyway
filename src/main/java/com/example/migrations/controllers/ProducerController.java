package com.example.migrations.controllers;

import com.example.migrations.controllers.api.ProducerApi;
import com.example.migrations.dto.ProducerDto;
import com.example.migrations.repository.ProducerRepo;
import com.example.migrations.service.ProducerService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

@Tag(name = "Producer controller", description = "Implementation of producer API")
@Validated
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
    public ResponseEntity<?> createProducer(@Parameter(name = "ProducerDTO",
            description = "Data transfer object of a producer")
                                                @Valid
                                                ProducerDto producerDto) {
        return producerService.createProducer(producerDto);
    }

    @Override
    public ResponseEntity<?> updateProducer(@Parameter(name = "ProducerDTO",
            description = "Data transfer object of a producer")
                                                @Valid
                                                ProducerDto producerDto) {
        return producerService.updateProducer(producerDto);
    }

    @Override
    public ResponseEntity<?> deleteProducer(@Parameter(name = "id",
            description = "Identificator of producer")
                                                Long id) {
        return producerService.deleteProducer(id);
    }
}
