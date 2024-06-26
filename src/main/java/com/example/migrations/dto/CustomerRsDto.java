package com.example.migrations.dto;

import com.example.migrations.entity.Customer;
import lombok.Getter;

@Getter
public class CustomerRsDto {
    private String title;
    private String address;
    private String phone;

    public CustomerRsDto(Customer customer) {
        this.title = customer.getTitle();
        this.address = customer.getAddress();
        this.phone = customer.getPhone();
    }
}
