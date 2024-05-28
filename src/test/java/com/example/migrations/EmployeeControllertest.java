package com.example.migrations;

import com.example.migrations.config.SpringBootApplicationTest;
import com.example.migrations.dto.EmployeeCreateDto;
import com.example.migrations.dto.EmployeeUpdateDto;
import com.example.migrations.entity.Employee;
import com.example.migrations.repository.EmployeeRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

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
public class EmployeeControllertest extends SpringBootApplicationTest {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    void getEmployees_Success() throws Exception {
        Employee employee= new Employee(EmployeeCreateDto.builder()
                .name("John")
                .surname("Doe")
                .papaname("Patronymich")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("1234 Main St")
                .phone("555-1234")
                .passport(123456789)
                .build());
        employee = employeeRepo.save(employee);
        webTestClient.get().uri("/api/employee")
                        .exchange()
                                .expectStatus().isOk()
                        .expectBody().jsonPath("$[0].name").isEqualTo("John");

    }

    @Test
    @Transactional
    void getEmployees_NotFound() throws Exception {
        employeeRepo.deleteAll();
        webTestClient.get().uri("/api/employee")
                        .exchange()
                                .expectStatus().isOk()
                        .expectBody().jsonPath("$").isEmpty();

    }

    @Test
    @Transactional
    void createEmployee_Success() throws Exception {
        EmployeeCreateDto dto = EmployeeCreateDto.builder()
                .name("Jane")
                .surname("Doe")
                .papaname("Patronymich")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("1234 Main St")
                .phone("555-1234")
                .passport(123456789)
                .build();
        webTestClient.post().uri("/api/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(objectMapper.writeValueAsString(dto))
                                        .exchange()
                                                .expectStatus().isOk()
                        .expectBody().jsonPath("$.name").isEqualTo("Jane");
    }

    @Test
    @Transactional
    void createEmployee_Fail_InvalidData() throws Exception {
        EmployeeCreateDto dto = EmployeeCreateDto.builder()
                .name(null)
                .surname("Doe")
                .papaname("Patronymich")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("1234 Main St")
                .phone("555-1234")
                .passport(123456789)
                .build();
        webTestClient.post().uri("/api/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(objectMapper.writeValueAsString(dto))
                                .exchange()
                                        .expectStatus().isNotFound();
    }

    @Test
    @Transactional
    void updateEmployee_Success() throws Exception {
        Employee employee= new Employee(EmployeeCreateDto.builder()
                .name("Old Name")
                .surname("Old Surname")
                .papaname("Old Papaname")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("Old Address")
                .phone("Old Phone")
                .passport(123456789)
                .build());
        employee = employeeRepo.save(employee);

        EmployeeCreateDto dto = EmployeeCreateDto.builder()
                .name("New Name")
                .surname("New Surname")
                .papaname("New Papaname")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("1234 Main St")
                .phone("555-1234")
                .passport(123456789)
                .build();
        webTestClient.put().uri("/api/employee/" + employee.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(objectMapper.writeValueAsString(dto))
                                        .exchange()
                                                .expectStatus().isOk()
                        .expectBody().jsonPath("$.name").isEqualTo("New Name");
    }

    @Test
    @Transactional
    void updateEmployee_Fail_NotFound() throws Exception {
        EmployeeUpdateDto dto =EmployeeUpdateDto.builder()
                .name("New Name")
                .surname("New Surname")
                .papaname("New Papaname")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("New Address")
                .phone("555-1234")
                .passport(123456789)
                .build();
        webTestClient.put().uri("/api/employee/999")
                        .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(objectMapper.writeValueAsString(dto))
                                        .exchange()
                                                .expectStatus().isNotFound();
    }

    @Test
    @Transactional
    void deleteEmployee_Success() throws Exception {
        Employee employee = new Employee(EmployeeCreateDto.builder()
                .name("Name")
                .surname("Surname")
                .papaname("Papaname")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("Address")
                .phone("Phone")
                .passport(123456789)
                .build());
        employee = employeeRepo.save(employee);
        webTestClient.delete().uri("/api/employee/" + employee.getId())
                        .exchange()
                                .expectStatus().isOk();
        webTestClient.get().uri("/api/employee/" + employee.getId())
                        .exchange()
                                .expectStatus().isNotFound();
    }

    @Test
    @Transactional
    void deleteEmployee_Fail_NotFound() throws Exception {
        webTestClient.delete().uri("/api/employee/999")
                        .exchange()
                                .expectStatus().isNotFound();
    }

    @Test
    @Transactional
    void getEmployeeById_Success() throws Exception {
        Employee employee = new Employee(EmployeeCreateDto.builder()
                .name("John")
                .surname("Surname")
                .papaname("Papaname")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("Address")
                .phone("Phone")
                .passport(123456789)
                .build());
        employee = employeeRepo.save(employee);
        webTestClient.get().uri("/api/employee/" + employee.getId())
                        .exchange()
                                .expectStatus().isOk().
                                expectBody().jsonPath("$.name").isEqualTo("John");
    }

    @Test
    @Transactional
    void getEmployeeById_Fail_NotFound() throws Exception {
        webTestClient.get().uri("/api/employee/999")
                        .exchange()
                                .expectStatus().isNotFound();
    }
}
