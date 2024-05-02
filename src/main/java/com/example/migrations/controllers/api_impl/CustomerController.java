package com.example.migrations.controllers.api_impl;

import com.example.migrations.controllers.CustomerApi;
import com.example.migrations.dto.CustomerDto;
import com.example.migrations.dto.CustomerUpdateDto;
import com.example.migrations.entity.Customer;
import com.example.migrations.service.CustomerService;
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
    public Customer createCustomer(CustomerDto customerDto) {
        return customerService.createCustomer(customerDto);
    }

    @Override
    public Customer updateCustomer(Long id, CustomerUpdateDto customerUpdateDto) {
        return customerService.updateCustomer(id, customerUpdateDto);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerService.deleteCustomer(id);
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerService.getCustomerById(id);
    }
}
