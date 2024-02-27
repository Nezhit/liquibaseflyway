package com.example.migrations.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "goods_orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_go")
    private Integer id;

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


}
