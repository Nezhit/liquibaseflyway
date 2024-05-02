package com.example.migrations.entity;

import com.example.migrations.dto.CustomerDto;
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
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
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

    public Customer(CustomerDto customerDto){
        this.title= customerDto.getTitle();
        this.phone= customerDto.getPhone();
        this.address= customerDto.getAddress();
    }
}
