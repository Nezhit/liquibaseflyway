package com.example.migrations.entity;

import com.example.migrations.dto.OrderCreateDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "goods_orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_goods", nullable = false)
    private Good good;

    @Column(name = "Order_Date", nullable = false)
    private LocalDate orderDate;

    @Column(name = "Arrive_Date", nullable = false)
    private LocalDate arriveDate;

    @ManyToOne
    @JoinColumn(name = "id_cust", nullable = false)
    private Customer customer;

    @Column(name = "Amount", nullable = false)
    private Integer amount;

    @Column(name = "Price", nullable = false, precision = 7, scale = 2)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "id_emp", nullable = false)
    private Employee employee;

    public Order(OrderCreateDto orderCreateDto) {
        this.amount = orderCreateDto.getAmount();
        this.employee = orderCreateDto.getEmployee();
        this.arriveDate = orderCreateDto.getArriveDate();
        this.orderDate = orderCreateDto.getOrderDate();
        this.customer = orderCreateDto.getCustomer();
        this.good = orderCreateDto.getGood();
        this.price = orderCreateDto.getPrice();
    }
}
