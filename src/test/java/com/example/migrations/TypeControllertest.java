package com.example.migrations;

import com.example.migrations.config.SpringBootApplicationTest;
import com.example.migrations.dto.TypeCreateDto;
import com.example.migrations.dto.TypeUpdateDto;
import com.example.migrations.entity.Type;
import com.example.migrations.entity.enums.ETypes;
import com.example.migrations.repository.TypeRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
public class TypeControllertest extends SpringBootApplicationTest {

    @Autowired
    private TypeRepo typeRepo;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    void getTypes_Success() {
        // Подготовка данных
        Type type = new Type(TypeCreateDto.builder()
                .title(ETypes.TV)
                .build());
        type = typeRepo.save(type);

        webTestClient.get().uri("/api/type")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].title").isEqualTo("TV");
    }

    @Test
    @Transactional
    void getTypes_NotFound() {
        typeRepo.deleteAll();

        webTestClient.get().uri("/api/type")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isEmpty();
    }

    @Test
    @Transactional
    void createType_Success() throws Exception {
        TypeCreateDto dto = TypeCreateDto.builder()
                .title(ETypes.TV)
                .build();

        webTestClient.post().uri("/api/type")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(objectMapper.writeValueAsString(dto))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.title").isEqualTo("TV");
    }

    @Test
    @Transactional
    void createType_Fail_InvalidData() throws Exception {
        TypeCreateDto dto = TypeCreateDto.builder()
                .title(null)
                .build();

        webTestClient.post().uri("/api/type")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(objectMapper.writeValueAsString(dto))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    @Transactional
    void updateType_Success() throws Exception {
        Type type = new Type(TypeCreateDto.builder()
                .title(ETypes.TV)
                .build());
        type = typeRepo.save(type);

        TypeUpdateDto dto = TypeUpdateDto.builder()
                .title(ETypes.VACUUM_CLEANER)
                .build();

        webTestClient.put().uri("/api/type/" + type.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(objectMapper.writeValueAsString(dto))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.title").isEqualTo("VACUUM_CLEANER");
    }

    @Test
    @Transactional
    void updateType_Fail_NotFound() throws Exception {
        TypeUpdateDto dto = TypeUpdateDto.builder()
                .title(ETypes.VACUUM_CLEANER)
                .build();

        webTestClient.put().uri("/api/type/999")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(objectMapper.writeValueAsString(dto))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    @Transactional
    void deleteType_Success() throws Exception {
        Type type = new Type(TypeCreateDto.builder()
                .title(ETypes.TV)
                .build());
        type = typeRepo.save(type);

        webTestClient.delete().uri("/api/type/" + type.getId())
                .exchange()
                .expectStatus().isOk();

        webTestClient.get().uri("/api/type/" + type.getId())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    @Transactional
    void deleteType_Fail_NotFound() throws Exception {
        webTestClient.delete().uri("/api/type/999")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    @Transactional
    void getTypeById_Success() throws Exception {
        Type type = new Type(TypeCreateDto.builder()
                .title(ETypes.TV)
                .build());
        type = typeRepo.save(type);

        webTestClient.get().uri("/api/type/" + type.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.title").isEqualTo("TV");
    }

    @Test
    @Transactional
    void getTypeById_Fail_NotFound() throws Exception {
        webTestClient.get().uri("/api/type/999")
                .exchange()
                .expectStatus().isNotFound();
    }
}
