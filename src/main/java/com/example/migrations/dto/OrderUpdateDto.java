package com.example.migrations.dto;

import com.example.migrations.entity.Customer;
import com.example.migrations.entity.Employee;
import com.example.migrations.entity.Good;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class OrderUpdateDto {
    private Long id;
    private Good good;
    private LocalDate orderDate;
    private LocalDate arriveDate;
    private Customer customer;
    private Integer amount;
    private BigDecimal price;
    private Employee employee;
}
