package com.example.migrations.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_emp")
    private Integer id;

    @Column(name = "Name", nullable = false, length = 15)
    private String name;

    @Column(name = "Surname", nullable = false, length = 20)
    private String surname;

    @Column(name = "Papaname", length = 20)
    private String papaname;

    @Column(name = "Birthdate", nullable = false)
    private LocalDate birthdate;

    @Column(name = "Address", nullable = false, length = 20)
    private String address;

    @Column(name = "Phone", nullable = false, length = 11)
    private String phone;

    @Column(name = "Passport", nullable = false, unique = true)
    private Integer passport;


}
