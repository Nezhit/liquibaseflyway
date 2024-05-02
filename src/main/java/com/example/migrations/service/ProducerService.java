package com.example.migrations.service;

import com.example.migrations.dto.ProducerDto;
import com.example.migrations.dto.ProducerUpdateDto;
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

    public Producer updateProducer(ProducerUpdateDto producerUpdateDto) {
        Producer producer = producerRepo.findById(producerUpdateDto.getId()).get();
        if(producerUpdateDto.getAddress()!=null) producer.setAddress(producerUpdateDto.getAddress());
        if(producerUpdateDto.getPhone()!=null) producer.setPhone(producerUpdateDto.getPhone());
        if(producer.getPhone()!=null) producer.setPhone(producer.getPhone());
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
