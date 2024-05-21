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
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    void getGoods_Success() throws Exception {
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

        mockMvc.perform(get("/api/good"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", is("Example Good")));
    }

    @Test
    @Transactional
    void getGoods_NotFound() throws Exception {
        goodRepo.deleteAll();

        mockMvc.perform(get("/api/good"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
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
        mockMvc.perform(post("/api/good")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("New Good")));
    }

    @Test
    @Transactional
    void createGood_Fail_InvalidData() throws Exception {
        GoodCreateDto dto =GoodCreateDto.builder()
                .title(null)
                .type(new Type(TypeCreateDto.builder().title(ETypes.TV).build()))
                .producer(new Producer(ProducerCreateDto.builder()
                        .address("Address")
                        .phone("213-123")
                        .title("Title")
                        .build()))
                .build();
        mockMvc.perform(post("/api/good")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
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
                .title("Update Good")
                .type(new Type(TypeCreateDto.builder().title(ETypes.TV).build()))
                .producer(new Producer(ProducerCreateDto.builder()
                        .address("Address")
                        .phone("213-123")
                        .title("Update Customer")
                        .build()))
                .build();
        mockMvc.perform(put("/api/good/" + good.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Updated Good")));
    }

    @Test
    @Transactional
    void updateGood_Fail_NotFound() throws Exception {
        GoodUpdateDto dto = GoodUpdateDto.builder()
                .title("Notexisted Good")
                .type(new Type(TypeCreateDto.builder().title(ETypes.TV).build()))
                .producer(new Producer(ProducerCreateDto.builder()
                        .address("Address")
                        .phone("213-123")
                        .title("Notexisted Customer")
                        .build()))
                .build();
        mockMvc.perform(put("/api/good/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void deleteGood_Success() throws Exception {
        Good good = new Good(GoodCreateDto.builder()
                .title(null)
                .type(new Type(TypeCreateDto.builder().title(ETypes.TV).build()))
                .producer(new Producer(ProducerCreateDto.builder()
                        .address("Address")
                        .phone("213-123")
                        .title("Title")
                        .build()))
                .build());
        good = goodRepo.save(good);

        mockMvc.perform(delete("/api/good/" + good.getId()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/good/" + good.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void deleteGood_Fail_NotFound() throws Exception {
        mockMvc.perform(delete("/api/good/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void getGoodById_Success() throws Exception {
        Good good = new Good(GoodCreateDto.builder()
                .title(null)
                .type(new Type(TypeCreateDto.builder().title(ETypes.TV).build()))
                .producer(new Producer(ProducerCreateDto.builder()
                        .address("Address")
                        .phone("213-123")
                        .title("Title")
                        .build()))
                .build());
        good = goodRepo.save(good);

        mockMvc.perform(get("/api/good/" + good.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Specific Good")));
    }

    @Test
    @Transactional
    void getGoodById_Fail_NotFound() throws Exception {
        mockMvc.perform(get("/api/good/999"))
                .andExpect(status().isNotFound());
    }
}

