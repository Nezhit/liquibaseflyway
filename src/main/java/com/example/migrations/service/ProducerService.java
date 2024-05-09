package com.example.migrations.service;

import com.example.migrations.dto.ProducerCreateDto;
import com.example.migrations.dto.ProducerRsDto;
import com.example.migrations.dto.ProducerUpdateDto;
import com.example.migrations.entity.Producer;
import com.example.migrations.repository.ProducerRepo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProducerService {
    private final ProducerRepo producerRepo;

    public ProducerService(ProducerRepo producerRepo) {
        this.producerRepo = producerRepo;
    }

    public List<ProducerRsDto> getProducers() {
        return producerRepo.findAll().stream().map(ProducerRsDto::new).collect(Collectors.toList());
    }

    public ProducerRsDto createProducer(ProducerCreateDto producerCreateDto) {
        Producer producer = new Producer(producerCreateDto);
        return new ProducerRsDto(producerRepo.save(producer));
    }

    public ProducerRsDto updateProducer(Long id, ProducerUpdateDto producerUpdateDto) {
        System.out.println("Performed dto = "+producerUpdateDto.toString());
        Producer producer = producerRepo.findById(id).orElseThrow(() -> new RuntimeException("Производитель не найден"));
        if (producerUpdateDto.getAddress() != null) producer.setAddress(producerUpdateDto.getAddress());
        if (producerUpdateDto.getPhone() != null) producer.setPhone(producerUpdateDto.getPhone());
        if (producerUpdateDto.getTitle() != null) producer.setTitle(producerUpdateDto.getTitle());
        return new ProducerRsDto(producerRepo.save(producer));
    }

    public void deleteProducer(Long id) {
        Producer producer = producerRepo.findById(id).orElseThrow(() -> new RuntimeException("Производитель не найден"));
        producerRepo.delete(producer);
    }

    public ProducerRsDto getProducerById(Long id) {
        return new ProducerRsDto(producerRepo.findById(id).orElseThrow(() -> new RuntimeException("Производитель не найден")));
    }
}
