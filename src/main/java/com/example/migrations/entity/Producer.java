package com.example.migrations.entity;

import com.example.migrations.dto.ProducerCreateDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "producers")
public class Producer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "Title", nullable = false, length = 15)
    private String title;

    @Column(name = "Adress", nullable = false, length = 20)
    private String address;

    @Column(name = "Phone", nullable = false, length = 11)
    private String phone;

    public Producer(ProducerCreateDto producerCreateDto) {
        this.title = producerCreateDto.getTitle();
        this.phone = producerCreateDto.getPhone();
        this.address = producerCreateDto.getAddress();
    }
}