package com.example.migrations.dto;

import com.example.migrations.entity.Customer;
import com.example.migrations.entity.Employee;
import com.example.migrations.entity.Good;
import lombok.Getter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class OrderUpdateDto {
    private Good good;
    private LocalDate orderDate;
    private LocalDate arriveDate;
    private Customer customer;
    private Integer amount;
    private BigDecimal price;
    private Employee employee;
}
