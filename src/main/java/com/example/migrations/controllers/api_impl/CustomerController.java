package com.example.migrations.controllers.api_impl;

import com.example.migrations.controllers.CustomerApi;
import com.example.migrations.dto.CustomerCreateDto;
import com.example.migrations.dto.CustomerRsDto;
import com.example.migrations.dto.CustomerUpdateDto;
import com.example.migrations.entity.Customer;
import com.example.migrations.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class CustomerController implements CustomerApi {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public List<CustomerRsDto> getCustomers() {
        return customerService.getCustomers();
    }

    @Override
    public CustomerRsDto createCustomer(CustomerCreateDto customerCreateDto) {
        return customerService.createCustomer(customerCreateDto);
    }

    @Override
    public CustomerRsDto updateCustomer(Long id, CustomerUpdateDto customerUpdateDto) {
        return customerService.updateCustomer(id, customerUpdateDto);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerService.deleteCustomer(id);
    }

    @Override
    public CustomerRsDto getCustomerById(Long id) {
        return customerService.getCustomerById(id);
    }
}
