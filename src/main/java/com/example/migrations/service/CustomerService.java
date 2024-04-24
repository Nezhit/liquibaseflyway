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
        System.out.println(customerDto.toString());
        if (customerDto.getTitle() == null || customerDto.getAddress() == null || customerDto.getPhone() == null) {
            throw new RuntimeException("Не все поля заполнены");
        }
        Customer customer = new Customer(customerDto.getTitle(), customerDto.getAddress(), customerDto.getPhone());
        customerRepo.save(customer);
        return customer;
    }

    public Customer updateCustomer(Long id, CustomerDto customerDto) {
        if (customerDto.getId() == null || customerDto.getTitle() == null || customerDto.getAddress() == null || customerDto.getPhone() == null) {
            throw new RuntimeException("Не все поля заполнены");
        }
        Customer customer = customerRepo.findById(id).orElse(null);
        if (customer == null) {
            throw new RuntimeException("Покупатель не найден");
        }
        customer.setAddress(customerDto.getAddress());
        customer.setPhone(customerDto.getPhone());
        customer.setTitle(customerDto.getTitle());
        customerRepo.save(customer);
        return customer;
    }

    public Customer deleteCustomer(Long id) {
        Customer customer = customerRepo.findById(id).get();
        customerRepo.delete(customer);
        return customer;
    }

    public List<Customer> getCustomers() {
        return customerRepo.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepo.findById(id).get();
    }
}

