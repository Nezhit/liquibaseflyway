package com.example.migrations;

import com.example.migrations.config.SpringBootApplicationTest;
import com.example.migrations.dto.GoodCreateDto;
import com.example.migrations.dto.GoodUpdateDto;
import com.example.migrations.dto.ProducerCreateDto;
import com.example.migrations.dto.TypeCreateDto;
import com.example.migrations.entity.Good;
import com.example.migrations.entity.Producer;
import com.example.migrations.entity.Type;
import com.example.migrations.entity.enums.ETypes;
import com.example.migrations.repository.GoodRepo;
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
public class GoodControllertest extends SpringBootApplicationTest {

    @Autowired
    private GoodRepo goodRepo;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    void getGoods_Success() {
        // Подготовка данных
        Good good = new Good(GoodCreateDto.builder()
                .title("Example Good")
                .type(new Type(TypeCreateDto.builder().title(ETypes.TV).build()))
                .producer(new Producer(ProducerCreateDto.builder()
                        .address("Address")
                        .phone("213-123")
                        .title("Title")
                        .build()))
                .build());
        good = goodRepo.save(good);

        webTestClient.get().uri("/api/good")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].title").isEqualTo("Example Good");
    }

    @Test
    @Transactional
    void getGoods_NotFound() {
        goodRepo.deleteAll();

        webTestClient.get().uri("/api/good")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isEmpty();
    }

    @Test
    @Transactional
    void createGood_Success() throws Exception {
        GoodCreateDto dto = GoodCreateDto.builder()
                .title("New Good")
                .type(new Type(TypeCreateDto.builder().title(ETypes.TV).build()))
                .producer(new Producer(ProducerCreateDto.builder()
                        .address("Address")
                        .phone("213-123")
                        .title("Title")
                        .build()))
                .build();

        webTestClient.post().uri("/api/good")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(objectMapper.writeValueAsString(dto))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.title").isEqualTo("New Good");
    }

    @Test
    @Transactional
    void createGood_Fail_InvalidData() throws Exception {
        GoodCreateDto dto = GoodCreateDto.builder()
                .title(null)
                .type(new Type(TypeCreateDto.builder().title(ETypes.TV).build()))
                .producer(new Producer(ProducerCreateDto.builder()
                        .address("Address")
                        .phone("213-123")
                        .title("Title")
                        .build()))
                .build();

        webTestClient.post().uri("/api/good")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(objectMapper.writeValueAsString(dto))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    @Transactional
    void updateGood_Success() throws Exception {
        Good good = new Good(GoodCreateDto.builder()
                .title("Old Good")
                .type(new Type(TypeCreateDto.builder().title(ETypes.TV).build()))
                .producer(new Producer(ProducerCreateDto.builder()
                        .address("Address")
                        .phone("213-123")
                        .title("Title")
                        .build()))
                .build());
        good = goodRepo.save(good);

        GoodUpdateDto dto = GoodUpdateDto.builder()
                .title("Updated Good")
                .type(new Type(TypeCreateDto.builder().title(ETypes.TV).build()))
                .producer(new Producer(ProducerCreateDto.builder()
                        .address("Address")
                        .phone("213-123")
                        .title("Updated Title")
                        .build()))
                .build();

        webTestClient.put().uri("/api/good/" + good.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(objectMapper.writeValueAsString(dto))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.title").isEqualTo("Updated Good");
    }

    @Test
    @Transactional
    void updateGood_Fail_NotFound() throws Exception {
        GoodUpdateDto dto = GoodUpdateDto.builder()
                .title("Nonexistent Good")
                .type(new Type(TypeCreateDto.builder().title(ETypes.TV).build()))
                .producer(new Producer(ProducerCreateDto.builder()
                        .address("Address")
                        .phone("213-123")
                        .title("Nonexistent Title")
                        .build()))
                .build();

        webTestClient.put().uri("/api/good/999")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(objectMapper.writeValueAsString(dto))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    @Transactional
    void deleteGood_Success() {
        Good good = new Good(GoodCreateDto.builder()
                .title("Good to Delete")
                .type(new Type(TypeCreateDto.builder().title(ETypes.TV).build()))
                .producer(new Producer(ProducerCreateDto.builder()
                        .address("Address")
                        .phone("213-123")
                        .title("Title")
                        .build()))
                .build());
        good = goodRepo.save(good);

        webTestClient.delete().uri("/api/good/" + good.getId())
                .exchange()
                .expectStatus().isOk();

        webTestClient.get().uri("/api/good/" + good.getId())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    @Transactional
    void deleteGood_Fail_NotFound() {
        webTestClient.delete().uri("/api/good/999")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    @Transactional
    void getGoodById_Success() {
        Good good = new Good(GoodCreateDto.builder()
                .title("Specific Good")
                .type(new Type(TypeCreateDto.builder().title(ETypes.TV).build()))
                .producer(new Producer(ProducerCreateDto.builder()
                        .address("Address")
                        .phone("213-123")
                        .title("Title")
                        .build()))
                .build());
        good = goodRepo.save(good);

        webTestClient.get().uri("/api/good/" + good.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.title").isEqualTo("Specific Good");
    }

    @Test
    @Transactional
    void getGoodById_Fail_NotFound() {
        webTestClient.get().uri("/api/good/999")
                .exchange()
                .expectStatus().isNotFound();
    }
}

