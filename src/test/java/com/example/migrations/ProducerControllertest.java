package com.example.migrations;

import com.example.migrations.config.SpringBootApplicationTest;
import com.example.migrations.dto.ProducerCreateDto;
import com.example.migrations.dto.ProducerUpdateDto;
import com.example.migrations.entity.Producer;
import com.example.migrations.repository.ProducerRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
public class ProducerControllertest extends SpringBootApplicationTest {

    @Autowired
    private ProducerRepo producerRepo;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    void getProducers_Success() {
        // Подготовка данных
        Producer producer = new Producer(ProducerCreateDto.builder()
                .title("Best Producer")
                .address("1234 Main St")
                .phone("555-1234")
                .build());
        producer = producerRepo.save(producer);

        webTestClient.get().uri("/api/producer")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].title").isEqualTo("Best Producer");
    }

    @Test
    @Transactional
    void getProducers_NotFound() {
        producerRepo.deleteAll();

        webTestClient.get().uri("/api/producer")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isEmpty();
    }

    @Test
    @Transactional
    void createProducer_Success() throws Exception {
        ProducerCreateDto dto = ProducerCreateDto.builder()
                .title("New Producer")
                .address("5678 Main St")
                .phone("555-6789")
                .build();

        webTestClient.post().uri("/api/producer")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(objectMapper.writeValueAsString(dto))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.title").isEqualTo("New Producer");
    }

    @Test
    @Transactional
    void createProducer_Fail_InvalidData() throws Exception {
        ProducerCreateDto dto = ProducerCreateDto.builder()
                .title(null)
                .address("5678 Main St")
                .phone("555-6789")
                .build();

        webTestClient.post().uri("/api/producer")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(objectMapper.writeValueAsString(dto))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    @Transactional
    void updateProducer_Success() throws Exception {
        Producer producer = new Producer(ProducerCreateDto.builder()
                .title("Title")
                .address("Address")
                .phone("Phone")
                .build());
        producer = producerRepo.save(producer);

        ProducerUpdateDto dto = ProducerUpdateDto.builder()
                .title("Upd")
                .address("UpAddress")
                .phone("UpdPhone")
                .build();

        webTestClient.put().uri("/api/producer/" + producer.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(objectMapper.writeValueAsString(dto))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.title").isEqualTo("Upd");
    }

    @Test
    @Transactional
    void updateProducer_Fail_NotFound() throws Exception {
        ProducerUpdateDto dto = ProducerUpdateDto.builder()
                .title("Nonexistent Title")
                .address("Nonexistent Address")
                .phone("Nonexistent Phone")
                .build();

        webTestClient.put().uri("/api/producer/999")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(objectMapper.writeValueAsString(dto))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    @Transactional
    void deleteProducer_Success() throws Exception {
        Producer producer = new Producer(ProducerCreateDto.builder()
                .title("Delete")
                .address("Delete Address")
                .phone("12345")
                .build());
        producer = producerRepo.save(producer);

        webTestClient.delete().uri("/api/producer/" + producer.getId())
                .exchange()
                .expectStatus().isOk();

        webTestClient.get().uri("/api/producer/" + producer.getId())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    @Transactional
    void deleteProducer_Fail_NotFound() throws Exception {
        webTestClient.delete().uri("/api/producer/999")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    @Transactional
    void getProducerById_Success() throws Exception {
        Producer producer = new Producer(ProducerCreateDto.builder()
                .title("Spec Producer")
                .address("Spec Address")
                .phone("Spec Phone")
                .build());
        producer = producerRepo.save(producer);

        webTestClient.get().uri("/api/producer/" + producer.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.title").isEqualTo("Spec Producer");
    }

    @Test
    @Transactional
    void getProducerById_Fail_NotFound() throws Exception {
        webTestClient.get().uri("/api/producer/999")
                .exchange()
                .expectStatus().isNotFound();
    }
}