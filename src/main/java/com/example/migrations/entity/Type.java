package com.example.migrations.entity;

import com.example.migrations.entity.enums.ETypes;
import jakarta.persistence.*;

@Entity
@Table(name = "types")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type")
    private Long id;
    @Column(name = "Title")
    private String Title;



}
