package com.example.migrations.service;

import com.example.migrations.dto.ProducerDto;
import com.example.migrations.entity.Producer;
import com.example.migrations.repository.ProducerRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {
    private final ProducerRepo producerRepo;

    public ProducerService(ProducerRepo producerRepo) {
        this.producerRepo = producerRepo;
    }

    public ResponseEntity<?> getProducers() {
        return ResponseEntity.ok(producerRepo.findAll());
    }

    public ResponseEntity<?> createProducer(ProducerDto producerDto) {
        Producer producer= new Producer();
        producer.setAddress(producerDto.getAddress());
        producer.setPhone(producerDto.getPhone());
        producer.setPhone(producer.getPhone());
        producerRepo.save(producer);
        return ResponseEntity.ok("Производитель создан");
    }

    public ResponseEntity<?> updateProducer(ProducerDto producerDto) {
        Producer producer=producerRepo.findById(producerDto.getId()).get();
        producer.setAddress(producerDto.getAddress());
        producer.setPhone(producerDto.getPhone());
        producer.setPhone(producer.getPhone());
        producerRepo.save(producer);
        return ResponseEntity.ok("Производитель обновлен");
    }

    public ResponseEntity<?> deleteProducer(Long id) {
        Producer producer=producerRepo.findById(id).get();
        producerRepo.delete(producer);
        return ResponseEntity.ok("Производитель удален");
    }
}
