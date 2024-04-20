package com.example.migrations.dto;

import com.example.migrations.entity.Customer;
import com.example.migrations.entity.Employee;
import com.example.migrations.entity.Good;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
public class OrderDto {
    private Long id;

    private Good good;

    private LocalDate orderDate;

    private LocalDate arriveDate;

    private Customer customer;

    private Integer amount;

    private BigDecimal price;

    private Employee employee;
}
