package com.example.migrations.service;

import com.example.migrations.dto.CustomerDto;
import com.example.migrations.entity.Customer;
import com.example.migrations.repository.CustomerRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepo customerRepo;

    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public ResponseEntity<?> createCustomer(CustomerDto customerDto) {
        System.out.println(customerDto.toString());
        if (customerDto.getTitle() == null || customerDto.getAddress() == null || customerDto.getPhone() == null) {
            return ResponseEntity.badRequest().body("Не все обязательные поля заполнены");
        }
        Customer customer = new Customer(customerDto.getTitle(), customerDto.getAddress(), customerDto.getPhone());
        customerRepo.save(customer);
        return ResponseEntity.ok("Запись добавлена");
    }

    public ResponseEntity<?> updateCustomer(CustomerDto customerDto) {
        if (customerDto.getId() == null || customerDto.getTitle() == null || customerDto.getAddress() == null || customerDto.getPhone() == null) {
            return ResponseEntity.badRequest().body("Не все обязательные поля заполнены");
        }
        Customer customer = customerRepo.findById(customerDto.getId()).orElse(null);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        customer.setAddress(customerDto.getAddress());
        customer.setPhone(customerDto.getPhone());
        customer.setTitle(customerDto.getTitle());
        customerRepo.save(customer);
        return ResponseEntity.ok("Пользователь с id = " + customer.getId() + " обновлен");
    }

    public ResponseEntity<?> deleteCustomer(Long id) {
        Customer customer=customerRepo.findById(id).get();
        customerRepo.delete(customer);
        return ResponseEntity.ok("Удален");
    }
}

