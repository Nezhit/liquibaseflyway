package com.example.migrations.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "producers")
public class Producer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prod")
    private Integer id;

    @Column(name = "Title", nullable = false, length = 15)
    private String title;

    @Column(name = "Adress", nullable = false, length = 20)
    private String address;

    @Column(name = "Phone", nullable = false, length = 11)
    private String phone;
}