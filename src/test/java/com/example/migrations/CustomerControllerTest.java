package com.example.migrations;

import com.example.migrations.config.SpringBootApplicationTest;
import com.example.migrations.dto.CustomerCreateDto;
import com.example.migrations.dto.CustomerUpdateDto;
import com.example.migrations.entity.Customer;
import com.example.migrations.repository.CustomerRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest extends SpringBootApplicationTest {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    void getCustomers_Success() {
        // Подготовка данных
        Customer customer = new Customer(CustomerCreateDto.builder()
                .title("John Doe")
                .address("1234 Main St")
                .phone("555-1234")
                .build());
        customer = customerRepo.save(customer);

        webTestClient.get().uri("/api/customer")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].title").isEqualTo("John Doe");
    }

    @Test
    @Transactional
    void getCustomers_NotFound() {
        customerRepo.deleteAll();

        webTestClient.get().uri("/api/customer")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isEmpty();
    }

    @Test
    @Transactional
    void createCustomer_Success() throws Exception {
        CustomerCreateDto dto = CustomerCreateDto.builder()
                .title("Jane")
                .address("1234 Main St")
                .phone("555-1234")
                .build();

        webTestClient.post().uri("/api/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(objectMapper.writeValueAsString(dto))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.title").isEqualTo("Jane");
    }

    @Test
    @Transactional
    void createCustomer_Fail_InvalidData() throws Exception {
        CustomerCreateDto dto = CustomerCreateDto.builder()
                .title(null)
                .address("1234 Main St")
                .phone("555-1234")
                .build();

        webTestClient.post().uri("/api/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(objectMapper.writeValueAsString(dto))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    @Transactional
    void updateCustomer_Success() throws Exception {
        Customer customer = new Customer(CustomerCreateDto.builder()
                .title("Old Name")
                .address("Old address")
                .phone("1234567890")
                .build());
        customer = customerRepo.save(customer);

        CustomerUpdateDto dto = CustomerUpdateDto.builder()
                .title("New Name")
                .address("New address")
                .phone("1234567890")
                .build();

        webTestClient.put().uri("/api/customer/" + customer.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(objectMapper.writeValueAsString(dto))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.title").isEqualTo("New Name");
    }

    @Test
    @Transactional
    void updateCustomer_Fail_NotFound() throws Exception {
        CustomerUpdateDto dto = CustomerUpdateDto.builder()
                .title("New Name")
                .address("New address")
                .phone("1234567890")
                .build();

        webTestClient.put().uri("/api/customer/999")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(objectMapper.writeValueAsString(dto))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    @Transactional
    void deleteCustomer_Success() {
        Customer customer = new Customer(CustomerCreateDto.builder()
                .title("John")
                .address("Some address")
                .phone("1234567890")
                .build());
        customer = customerRepo.save(customer);

        webTestClient.delete().uri("/api/customer/" + customer.getId())
                .exchange()
                .expectStatus().isOk();

        webTestClient.get().uri("/api/customer/" + customer.getId())
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath("$.message").isEqualTo("Покупатель не найден");
    }

    @Test
    @Transactional
    void deleteCustomer_Fail_NotFound() {
        webTestClient.delete().uri("/api/customer/999")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    @Transactional
    void getCustomerById_Success() {
        Customer customer = new Customer(CustomerCreateDto.builder()
                .title("John")
                .address("Some address")
                .phone("1234567890")
                .build());
        customer = customerRepo.save(customer);
        System.out.println("ID = " + customer.getId());
        webTestClient.get().uri("/api/customer/" + customer.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.title").isEqualTo("John");
    }

    @Test
    @Transactional
    void getCustomerById_Fail_NotFound() {
        webTestClient.get().uri("/api/customer/999")
                .exchange()
                .expectStatus().isNotFound();
    }
}
