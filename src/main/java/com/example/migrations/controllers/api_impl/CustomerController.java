package com.example.migrations.controllers.api_impl;

import com.example.migrations.controllers.CustomerApi;
import com.example.migrations.dto.CustomerCreateDto;
import com.example.migrations.dto.CustomerRsDto;
import com.example.migrations.dto.CustomerUpdateDto;
import com.example.migrations.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class CustomerController implements CustomerApi {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public ResponseEntity<List<CustomerRsDto>> getCustomers() {
        List<CustomerRsDto> customers = customerService.getCustomers();
        return ResponseEntity.ok(customers);
    }

    @Override
    public ResponseEntity<CustomerRsDto> createCustomer(CustomerCreateDto customerCreateDto) {
        CustomerRsDto createdCustomer = customerService.createCustomer(customerCreateDto);
        return ResponseEntity.ok(createdCustomer);
    }

    @Override
    public ResponseEntity<CustomerRsDto> updateCustomer(Long id, CustomerUpdateDto customerUpdateDto) {
            CustomerRsDto updatedCustomer = customerService.updateCustomer(id, customerUpdateDto);
            return ResponseEntity.ok(updatedCustomer);
    }

    @Override
    public ResponseEntity<Void> deleteCustomer(Long id) {
            customerService.deleteCustomer(id);
            return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<CustomerRsDto> getCustomerById(Long id) {
            CustomerRsDto customer = customerService.getCustomerById(id);
            return ResponseEntity.ok(customer);
    }
}
