package com.example.migrations.dto;

import com.example.migrations.entity.Customer;
import com.example.migrations.entity.Employee;
import com.example.migrations.entity.Good;
import com.example.migrations.entity.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class OrderRsDto {
    private Good good;
    private LocalDate orderDate;
    private LocalDate arriveDate;
    private Customer customer;
    private Integer amount;
    private BigDecimal price;
    private Employee employee;

    public OrderRsDto(Order order) {
        this.good = order.getGood();
        this.orderDate = order.getOrderDate();
        this.arriveDate = order.getArriveDate();
        this.customer = order.getCustomer();
        this.amount = order.getAmount();
        this.price = order.getPrice();
        this.employee = order.getEmployee();
    }
}
