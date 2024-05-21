package com.example.migrations.service;

import com.example.migrations.dto.CustomerCreateDto;
import com.example.migrations.dto.CustomerRsDto;
import com.example.migrations.dto.CustomerUpdateDto;
import com.example.migrations.entity.Customer;
import com.example.migrations.repository.CustomerRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomerService {
    private final CustomerRepo customerRepo;

    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public CustomerRsDto createCustomer(CustomerCreateDto customerCreateDto) {
        Customer customer = new Customer(customerCreateDto);
        return new CustomerRsDto(customerRepo.save(customer));
    }

    public CustomerRsDto updateCustomer(Long id, CustomerUpdateDto customerUpdateDto) {
        Customer customer = customerRepo.findById(id).orElseThrow(() -> new RuntimeException("Покупатель не найден"));
        if (customerUpdateDto.getAddress() != null) customer.setAddress(customerUpdateDto.getAddress());
        if (customerUpdateDto.getPhone() != null) customer.setPhone(customerUpdateDto.getPhone());
        if (customerUpdateDto.getTitle() != null) customer.setTitle(customerUpdateDto.getTitle());
        return new CustomerRsDto(customerRepo.save(customer));
    }

    public void deleteCustomer(Long id) {
        Customer customer = customerRepo.findById(id).orElseThrow(() -> new RuntimeException("Покупатель не найден"));
        customerRepo.delete(customer);
    }

    public List<CustomerRsDto> getCustomers() {
        return customerRepo.findAll().stream().map(CustomerRsDto::new).collect(Collectors.toList());
    }

    public CustomerRsDto getCustomerById(Long id) {
        System.out.println("ID = " + id);
        return new CustomerRsDto(customerRepo.findById(id).orElseThrow(() -> new RuntimeException("Покупатель не найден")));
    }
}

