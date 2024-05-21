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
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    void getTypes_Success() throws Exception {
        Type type = new Type(TypeCreateDto.builder()
                .title(ETypes.TV)
                .build());
        type = typeRepo.save(type);

        mockMvc.perform(get("/api/type"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", is("TV")));
    }

    @Test
    @Transactional
    void getTypes_NotFound() throws Exception {
        typeRepo.deleteAll();

        mockMvc.perform(get("/api/type"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    void createType_Success() throws Exception {
        TypeCreateDto dto = TypeCreateDto.builder()
                .title(ETypes.TV)
                .build();
        System.out.println(objectMapper.writeValueAsString(dto));
        mockMvc.perform(post("/api/type")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("TV")));
    }

    @Test
    @Transactional
    void createType_Fail_InvalidData() throws Exception {
        TypeCreateDto dto = TypeCreateDto.builder()
                .title(null)
                .build();
        mockMvc.perform(post("/api/type")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
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
        System.out.println(type.getId());
        mockMvc.perform(put("/api/type/" + type.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("VACUUM_CLEANER")));
    }

    @Test
    @Transactional
    void updateType_Fail_NotFound() throws Exception {
        TypeUpdateDto dto = TypeUpdateDto.builder()
                .title(ETypes.VACUUM_CLEANER)
                .build();

        mockMvc.perform(put("/api/type/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void deleteType_Success() throws Exception {
        Type type = new Type(TypeCreateDto.builder()
                .title(ETypes.TV)
                .build());
        type = typeRepo.save(type);

        mockMvc.perform(delete("/api/type/" + type.getId()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/type/" + type.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void deleteType_Fail_NotFound() throws Exception {
        mockMvc.perform(delete("/api/type/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void getTypeById_Success() throws Exception {
        Type type = new Type(TypeCreateDto.builder()
                .title(ETypes.TV)
                .build());
        type = typeRepo.save(type);

        mockMvc.perform(get("/api/type/" + type.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("TV")));
    }

    @Test
    @Transactional
    void getTypeById_Fail_NotFound() throws Exception {
        mockMvc.perform(get("/api/type/999"))
                .andExpect(status().isNotFound());
    }
}

