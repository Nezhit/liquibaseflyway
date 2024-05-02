package com.example.migrations.service;

import com.example.migrations.dto.ProducerDto;
import com.example.migrations.entity.Producer;
import com.example.migrations.repository.ProducerRepo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProducerService {
    private final ProducerRepo producerRepo;

    public ProducerService(ProducerRepo producerRepo) {
        this.producerRepo = producerRepo;
    }

    public List<Producer> getProducers() {
        return producerRepo.findAll();
    }

    public Producer createProducer(ProducerDto producerDto) {
        Producer producer = new Producer(producerDto);
        return producerRepo.save(producer);
    }

    public Producer updateProducer(ProducerDto producerDto) {
        Producer producer = producerRepo.findById(producerDto.getId()).get();
        producer.setAddress(producerDto.getAddress());
        producer.setPhone(producerDto.getPhone());
        producer.setPhone(producer.getPhone());
        return producerRepo.save(producer);
    }

    public void deleteProducer(Long id) {
        Producer producer = producerRepo.findById(id).get();
        producerRepo.delete(producer);
    }

    public Producer getProducerById(Long id) {
        return producerRepo.findById(id).get();
    }
}
