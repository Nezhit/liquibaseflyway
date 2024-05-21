package com.example.migrations;

import com.example.migrations.config.SpringBootApplicationTest;
import com.example.migrations.dto.CustomerCreateDto;
import com.example.migrations.dto.EmployeeCreateDto;
import com.example.migrations.dto.GoodCreateDto;
import com.example.migrations.dto.OrderCreateDto;
import com.example.migrations.dto.OrderUpdateDto;
import com.example.migrations.dto.ProducerCreateDto;
import com.example.migrations.dto.TypeCreateDto;
import com.example.migrations.entity.Customer;
import com.example.migrations.entity.Employee;
import com.example.migrations.entity.Good;
import com.example.migrations.entity.Order;
import com.example.migrations.entity.Producer;
import com.example.migrations.entity.Type;
import com.example.migrations.entity.enums.ETypes;
import com.example.migrations.repository.OrderRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
public class OrderControllertest extends SpringBootApplicationTest {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    void getOrders_Success() throws Exception {
        Order order = new Order(OrderCreateDto.builder()
                .good(new Good(GoodCreateDto.builder()
                        .title("Example Good")
                        .type(new Type(TypeCreateDto.builder().title(ETypes.TV).build()))
                        .producer(new Producer(ProducerCreateDto.builder()
                                .address("Address")
                                .phone("213-123")
                                .title("Title")
                                .build()))
                        .build()))
                .orderDate(LocalDate.now())
                .arriveDate(LocalDate.now().plusDays(1))
                .customer(new Customer(CustomerCreateDto.builder()
                        .title("John Doe")
                        .address("1234 Main St")
                        .phone("555-1234")
                        .build()))
                .amount(10)
                .price(new BigDecimal("199.99"))
                .employee(new Employee(EmployeeCreateDto.builder()
                        .name("John")
                        .surname("Doe")
                        .papaname("Patronymich")
                        .birthdate(LocalDate.of(1990, 1, 1))
                        .address("1234 Main St")
                        .phone("555-1234")
                        .passport(123456789)
                        .build()))
                .build());
        order = orderRepo.save(order);

        mockMvc.perform(get("/api/order"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].amount", is(10)));
    }

    @Test
    @Transactional
    void getOrders_NotFound() throws Exception {
        orderRepo.deleteAll();

        mockMvc.perform(get("/api/order"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    void createOrder_Success() throws Exception {
        OrderCreateDto dto = OrderCreateDto.builder()
                .good(new Good(GoodCreateDto.builder()
                        .title("Example Good")
                        .type(new Type(TypeCreateDto.builder().title(ETypes.TV).build()))
                        .producer(new Producer(ProducerCreateDto.builder()
                                .address("Address")
                                .phone("213-123")
                                .title("Title")
                                .build()))
                        .build()))
                .orderDate(LocalDate.now())
                .arriveDate(LocalDate.now().plusDays(1))
                .customer(new Customer(CustomerCreateDto.builder()
                        .title("John Doe")
                        .address("1234 Main St")
                        .phone("555-1234")
                        .build()))
                .amount(5)
                .price(new BigDecimal("199.99"))
                .employee(new Employee(EmployeeCreateDto.builder()
                        .name("John")
                        .surname("Doe")
                        .papaname("Patronymich")
                        .birthdate(LocalDate.of(1990, 1, 1))
                        .address("1234 Main St")
                        .phone("555-1234")
                        .passport(123456789)
                        .build()))
                .build();

        mockMvc.perform(post("/api/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount", is(5)));
    }

    @Test
    @Transactional
    void createOrder_Fail_InvalidData() throws Exception {
        OrderCreateDto dto = OrderCreateDto.builder()
                .good(null)
                .orderDate(null)
                .arriveDate(null)
                .customer(null)
                .amount(null)
                .price(null)
                .employee(null)
                .build();

        mockMvc.perform(post("/api/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    void updateOrder_Success() throws Exception {
        Order order = new Order(OrderCreateDto.builder()
                .good(new Good(GoodCreateDto.builder()
                        .title("Example Good")
                        .type(new Type(TypeCreateDto.builder().title(ETypes.TV).build()))
                        .producer(new Producer(ProducerCreateDto.builder()
                                .address("Address")
                                .phone("213-123")
                                .title("Title")
                                .build()))
                        .build()))
                .orderDate(LocalDate.now())
                .arriveDate(LocalDate.now().plusDays(1))
                .customer(new Customer(CustomerCreateDto.builder()
                        .title("John Doe")
                        .address("1234 Main St")
                        .phone("555-1234")
                        .build()))
                .amount(10)
                .price(new BigDecimal("199.99"))
                .employee(new Employee(EmployeeCreateDto.builder()
                        .name("John")
                        .surname("Doe")
                        .papaname("Patronymich")
                        .birthdate(LocalDate.of(1990, 1, 1))
                        .address("1234 Main St")
                        .phone("555-1234")
                        .passport(123456789)
                        .build()))
                .build());
        order = orderRepo.save(order);

        OrderUpdateDto dto = OrderUpdateDto.builder()
                .good(new Good(GoodCreateDto.builder()
                        .title("Example Good")
                        .type(new Type(TypeCreateDto.builder().title(ETypes.TV).build()))
                        .producer(new Producer(ProducerCreateDto.builder()
                                .address("Address")
                                .phone("213-123")
                                .title("Title")
                                .build()))
                        .build()))
                .orderDate(LocalDate.now())
                .arriveDate(LocalDate.now().plusDays(1))
                .customer(new Customer(CustomerCreateDto.builder()
                        .title("John Doe")
                        .address("1234 Main St")
                        .phone("555-1234")
                        .build()))
                .amount(30)
                .price(new BigDecimal("199.99"))
                .employee(new Employee(EmployeeCreateDto.builder()
                        .name("John")
                        .surname("Doe")
                        .papaname("Patronymich")
                        .birthdate(LocalDate.of(1990, 1, 1))
                        .address("1234 Main St")
                        .phone("555-1234")
                        .passport(123456789)
                        .build()))
                .build();

        mockMvc.perform(put("/api/order/" + order.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount", is(30)));
    }

    @Test
    @Transactional
    void updateOrder_Fail_NotFound() throws Exception {
        OrderUpdateDto dto = OrderUpdateDto.builder()
                .good(new Good(GoodCreateDto.builder()
                        .title("Example Good")
                        .type(new Type(TypeCreateDto.builder().title(ETypes.TV).build()))
                        .producer(new Producer(ProducerCreateDto.builder()
                                .address("Address")
                                .phone("213-123")
                                .title("Title")
                                .build()))
                        .build()))
                .orderDate(LocalDate.now())
                .arriveDate(LocalDate.now().plusDays(1))
                .customer(new Customer(CustomerCreateDto.builder()
                        .title("John Doe")
                        .address("1234 Main St")
                        .phone("555-1234")
                        .build()))
                .amount(30)
                .price(new BigDecimal("199.99"))
                .employee(new Employee(EmployeeCreateDto.builder()
                        .name("John")
                        .surname("Doe")
                        .papaname("Patronymich")
                        .birthdate(LocalDate.of(1990, 1, 1))
                        .address("1234 Main St")
                        .phone("555-1234")
                        .passport(123456789)
                        .build()))
                .build();

        mockMvc.perform(put("/api/order/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void deleteOrder_Success() throws Exception {
        Order order = new Order(OrderCreateDto.builder()
                .good(new Good(GoodCreateDto.builder()
                        .title("Example Good")
                        .type(new Type(TypeCreateDto.builder().title(ETypes.TV).build()))
                        .producer(new Producer(ProducerCreateDto.builder()
                                .address("Address")
                                .phone("213-123")
                                .title("Title")
                                .build()))
                        .build()))
                .orderDate(LocalDate.now())
                .arriveDate(LocalDate.now().plusDays(1))
                .customer(new Customer(CustomerCreateDto.builder()
                        .title("John Doe")
                        .address("1234 Main St")
                        .phone("555-1234")
                        .build()))
                .amount(10)
                .price(new BigDecimal("199.99"))
                .employee(new Employee(EmployeeCreateDto.builder()
                        .name("John")
                        .surname("Doe")
                        .papaname("Patronymich")
                        .birthdate(LocalDate.of(1990, 1, 1))
                        .address("1234 Main St")
                        .phone("555-1234")
                        .passport(123456789)
                        .build()))
                .build());
        order = orderRepo.save(order);

        mockMvc.perform(delete("/api/order/" + order.getId()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/order/" + order.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void deleteOrder_Fail_NotFound() throws Exception {
        mockMvc.perform(delete("/api/order/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void getOrderById_Success() throws Exception {
        Order order = new Order(OrderCreateDto.builder()
                .good(new Good(GoodCreateDto.builder()
                        .title("Example Good")
                        .type(new Type(TypeCreateDto.builder().title(ETypes.TV).build()))
                        .producer(new Producer(ProducerCreateDto.builder()
                                .address("Address")
                                .phone("213-123")
                                .title("Title")
                                .build()))
                        .build()))
                .orderDate(LocalDate.now())
                .arriveDate(LocalDate.now().plusDays(1))
                .customer(new Customer(CustomerCreateDto.builder()
                        .title("John Doe")
                        .address("1234 Main St")
                        .phone("555-1234")
                        .build()))
                .amount(10)
                .price(new BigDecimal("199.99"))
                .employee(new Employee(EmployeeCreateDto.builder()
                        .name("John")
                        .surname("Doe")
                        .papaname("Patronymich")
                        .birthdate(LocalDate.of(1990, 1, 1))
                        .address("1234 Main St")
                        .phone("555-1234")
                        .passport(123456789)
                        .build()))
                .build());
        order = orderRepo.save(order);

        mockMvc.perform(get("/api/order/" + order.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount", is(10)));
    }

    @Test
    @Transactional
    void getOrderById_Fail_NotFound() throws Exception {
        mockMvc.perform(get("/api/order/999"))
                .andExpect(status().isNotFound());
    }
}

