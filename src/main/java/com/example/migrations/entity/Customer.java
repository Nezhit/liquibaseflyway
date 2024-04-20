package com.example.migrations.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cust")
    private Long id;

    @Column(name = "Title", nullable = false, length = 15)
    private String title;

    @Column(name = "Address", nullable = false, length = 20)
    private String address;

    @Column(name = "Phone", nullable = false, length = 11)
    private String phone;

    public Customer(String title, String address, String phone) {
        this.title = title;
        this.address = address;
        this.phone = phone;
    }

    public Customer(Long id, String title, String address, String phone) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.phone = phone;
    }
}
