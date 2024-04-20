package com.example.migrations.controllers;

import com.example.migrations.controllers.api.CustomerApi;
import com.example.migrations.dto.CustomerDto;
import com.example.migrations.entity.Customer;
import com.example.migrations.repository.CustomerRepo;
import com.example.migrations.service.CustomerService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@Tag(name = "Customer controller", description = "Implementation of customer API")
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
    public ResponseEntity<?> createCustomer(@Parameter(name = "CustomerDTO",
            description = "Data transfer object of a customer")
                                                @Valid
                                                @RequestBody CustomerDto customerDto) {
        return customerService.createCustomer(customerDto);
    }

    @Override
    public ResponseEntity<?> updateCustomer(@Parameter(name = "CustomerDTO",
            description = "Data transfer object of a customer")
                                                @Valid
                                                @RequestBody CustomerDto customerDto) {
        return customerService.updateCustomer(customerDto);
    }

    @Override
    public ResponseEntity<?> deleteCustomer(@Parameter(name = "id",
            description = "Identificator of customer")
                                                @PathVariable Long id) {
      return customerService.deleteCustomer(id);
    }
}
