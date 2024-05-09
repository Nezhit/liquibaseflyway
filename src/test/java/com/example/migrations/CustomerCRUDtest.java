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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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

@SpringBootTest
public class CustomerCRUDtest extends SpringBootApplicationTest {
    @Autowired
    private  CustomerRepo customerRepo;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;



    @Test
    @Transactional
    void getCustomers_Success() throws Exception {
        // Подготовка данных
        Customer customer = new Customer(CustomerCreateDto.builder()
                .title("John Doe")
                .address("1234 Main St")
                .phone("555-1234")
                .build());
        customer = customerRepo.save(customer);


        mockMvc.perform(get("/api/customer"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(is("John Doe")));
    }

    @Test
    @Transactional
    void getCustomers_NotFound() throws Exception {
        customerRepo.deleteAll();
        mockMvc.perform(get("/api/customer"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }
    @Test
    @Transactional
    void createCustomer_Success() throws Exception {
        Customer dto = new Customer(CustomerCreateDto.builder()
                .title("Jane")
                .address("1234 Main St")
                .phone("555-1234")
                .build());
        mockMvc.perform(post("/api/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Jane")));
    }
    @Test
    @Transactional
    void createCustomer_Fail_InvalidData() throws Exception {
        CustomerCreateDto dto = CustomerCreateDto.builder()
                .title(null)
                .address("1234 Main St")
                .phone("555-1234")
                .build();
        mockMvc.perform(post("/api/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isNotFound());
    }
    @Test
    @Transactional
    void updateCustomer_Success() throws Exception {
        Customer customer = new Customer(CustomerCreateDto.builder()
                .phone("Old Name")
                .address("1234567890")
                .title("Old address")
                .build());
        customer = customerRepo.save(customer);
        CustomerUpdateDto dto = CustomerUpdateDto.builder()
                .phone("1234567890")
                .address("New address")
                .title("New Name")
                .build();

        mockMvc.perform(put("/api/customer/" + customer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("New Name")));
    }
    @Test
    @Transactional
    void updateCustomer_Fail_NotFound() throws Exception {
        CustomerUpdateDto dto= CustomerUpdateDto.builder()
                .address("New address")
                .phone("1234567890")
                .title("New Name")
                .build();
        mockMvc.perform(put("/api/customer/999") // ID не существует
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isNotFound());
    }
    @Test
    @Transactional
    void deleteCustomer_Success() throws Exception {
        Customer customer = new Customer(CustomerCreateDto.builder()
                .title("John")
                .address("Some address")
                .phone("1234567890")
                .build());
        customer = customerRepo.save(customer);

        mockMvc.perform(delete("/api/customer/" + customer.getId()))
                .andExpect(status().isOk());
        System.out.println("AAAA"+customer.getId());
        mockMvc.perform(get("/api/customer/" + customer.getId()))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("Покупатель не найден")));
    }
    @Test
    @Transactional
    void deleteCustomer_Fail_NotFound() throws Exception {
        mockMvc.perform(delete("/api/customer/999"))
                .andExpect(status().isNotFound());
    }
    @Test
    @Transactional
    void getCustomerById_Success() throws Exception {
        Customer customer = new Customer(CustomerCreateDto.builder()
                .title("John")
                .address("Some address")
                .phone("1234567890")
                .build());
        customer = customerRepo.save(customer);

        mockMvc.perform(get("/api/customer/" + customer.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("John")));
    }
    @Test
    @Transactional
    void getCustomerById_Fail_NotFound() throws Exception {
        mockMvc.perform(get("/api/customer/999"))
                .andExpect(status().isNotFound());
    }


}
