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

import java.util.List;

@RestController
public class CustomerController implements CustomerApi {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @Override
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @Override
    public Customer createCustomer(@RequestBody CustomerDto customerDto) {
        return customerService.createCustomer(customerDto);
    }

    @Override
    public Customer updateCustomer(@PathVariable Long id,@RequestBody CustomerDto customerDto) {
        return customerService.updateCustomer(id,customerDto);
    }

    @Override
    public Customer deleteCustomer(@PathVariable Long id) {
      return customerService.deleteCustomer(id);
    }
}
