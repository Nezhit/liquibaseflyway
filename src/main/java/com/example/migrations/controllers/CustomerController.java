package com.example.migrations.controllers;

import com.example.migrations.controllers.api.CustomerApi;
import com.example.migrations.dto.CustomerDto;
import com.example.migrations.entity.Customer;
import com.example.migrations.repository.CustomerRepo;
import com.example.migrations.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController implements CustomerApi {
    private final CustomerRepo customerRepo;
    private final CustomerService customerService;

    public CustomerController(CustomerRepo customerRepo, CustomerService customerService) {
        this.customerRepo = customerRepo;
        this.customerService = customerService;
    }

    @Override
    public ResponseEntity<?> getCustomers() {
        return ResponseEntity.ok(customerRepo.findAll());
    }

    @Override
    public ResponseEntity<?> createCustomer(@RequestBody CustomerDto customerDto) {
        return customerService.createCustomer(customerDto);
    }

    @Override
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerDto customerDto) {
        return customerService.updateCustomer(customerDto);
    }

    @Override
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
      return customerService.deleteCustomer(id);
    }
}
