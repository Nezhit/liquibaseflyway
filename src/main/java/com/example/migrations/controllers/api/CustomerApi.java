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
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface CustomerApi {
    @GetMapping("/api/getCustomers")
    public ResponseEntity<?> getCustomers();
    @PostMapping("/api/createCustomer")
    public ResponseEntity<?>createCustomer(@RequestBody CustomerDto customerDto);
    @PutMapping("/api/updateCustomer")
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerDto customerDto);
    @DeleteMapping("/api/deleteCustomer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id);

}
