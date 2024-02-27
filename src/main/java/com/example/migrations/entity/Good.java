package com.example.migrations.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "goods")
public class Good {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_good")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_Type", nullable = false)
    private Type type;

    @Column(name = "Title", nullable = false, length = 15)
    private String title;

    @ManyToOne
    @JoinColumn(name = "id_prod", nullable = false)
    private Producer producer;
}
