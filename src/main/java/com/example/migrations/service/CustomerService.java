package com.example.migrations.service;

import com.example.migrations.dto.CustomerDto;
import com.example.migrations.entity.Customer;
import com.example.migrations.repository.CustomerRepo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepo customerRepo;

    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public Customer createCustomer(CustomerDto customerDto) {
        if (customerDto.getTitle() == null || customerDto.getAddress() == null || customerDto.getPhone() == null) {
            throw new RuntimeException("Не все поля заполнены");
        }
        Customer customer = new Customer(customerDto);
        return customerRepo.save(customer);
    }

    public Customer updateCustomer(Long id, CustomerDto customerDto) {
        if (customerDto.getId() == null || customerDto.getTitle() == null || customerDto.getAddress() == null || customerDto.getPhone() == null) {
            throw new RuntimeException("Не все поля заполнены");
        }
        Customer customer = customerRepo.findById(id).orElseThrow(() -> new RuntimeException("Покупатель не найден"));
        customer.setAddress(customerDto.getAddress());
        customer.setPhone(customerDto.getPhone());
        customer.setTitle(customerDto.getTitle());
        return customerRepo.save(customer);
    }

    public void deleteCustomer(Long id) {
        Customer customer = customerRepo.findById(id).get();
        customerRepo.delete(customer);
    }

    public List<Customer> getCustomers() {
        return customerRepo.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepo.findById(id).get();
    }
}

