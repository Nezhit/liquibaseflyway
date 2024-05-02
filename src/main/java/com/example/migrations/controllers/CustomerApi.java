package com.example.migrations.controllers;

import com.example.migrations.dto.CustomerDto;
import com.example.migrations.entity.Customer;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@RequestMapping("/api/customer")
public interface CustomerApi {
    @GetMapping
    public List<Customer> getCustomers();

    @PostMapping
    public Customer createCustomer(@RequestBody CustomerDto customerDto);

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto);

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id);

    @GetMapping("/{id}")
    public Customer getCustomerById(Long id);

}
