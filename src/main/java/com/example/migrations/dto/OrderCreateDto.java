package com.example.migrations.dto;

import com.example.migrations.entity.Customer;
import com.example.migrations.entity.Employee;
import com.example.migrations.entity.Good;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
public class OrderCreateDto {
    @NotNull(message = "Good cannot be null")
    private Good good;

    @NotNull(message = "Order date cannot be null")
    @PastOrPresent(message = "Order date must be in the past or present")
    private LocalDate orderDate;

    @NotNull(message = "Arrive date cannot be null")
    private LocalDate arriveDate;

    @NotNull(message = "Customer date cannot be null")
    private Customer customer;

    @NotNull(message = "Amount cannot be null")
    private Integer amount;

    @NotNull(message = "Price cannot be null")
    private BigDecimal price;

    @NotNull(message = "Employee cannot be null")
    private Employee employee;
}
