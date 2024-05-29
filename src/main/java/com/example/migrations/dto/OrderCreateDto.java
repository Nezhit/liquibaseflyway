package com.example.migrations.dto;

import com.example.migrations.entity.Customer;
import com.example.migrations.entity.Employee;
import com.example.migrations.entity.Good;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Schema(description = "Data Transfer Object for placing a new order")
public class OrderCreateDto {
    @Schema(description = "Good associated with the order")
    private Good good;

    @Schema(description = "Order date", example = "2023-01-01", required = true)
    @NotNull(message = "Order date cannot be null")
    @PastOrPresent(message = "Order date must be in the past or present")
    private LocalDate orderDate;

    @Schema(description = "Arrive date", example = "2023-01-15")
    private LocalDate arriveDate;

    @Schema(description = "Customer who placed the order")
    private Customer customer;

    @Schema(description = "Amount of goods ordered", example = "10", required = true)
    @NotNull(message = "Amount cannot be null")
    @Min(value = 1, message = "Amount must be at least 1")
    private Integer amount;

    @Schema(description = "Price of the order", example = "100.00", required = true)
    @NotNull(message = "Price cannot be null")
    @Digits(integer = 10, fraction = 2, message = "Price must be a valid monetary amount")
    private BigDecimal price;

    @Schema(description = "Employee who processed the order")
    private Employee employee;
}
