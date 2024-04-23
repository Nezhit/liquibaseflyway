package com.example.migrations.controllers.api;

import com.example.migrations.dto.CustomerDto;
import com.example.migrations.entity.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/customer")
public interface CustomerApi {
    @GetMapping
    public List<Customer> getCustomers();
    @PostMapping
    public Customer createCustomer(@RequestBody CustomerDto customerDto);
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id,@RequestBody CustomerDto customerDto);
    @DeleteMapping("/{id}")
    public Customer deleteCustomer(@PathVariable Long id);

}
