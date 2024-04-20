package com.example.migrations.repository;

import com.example.migrations.entity.Customer;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
@Tag(name = "Customer Repository", description = "Repository extending Jpa Repository for Spring data jpa")
public interface CustomerRepo extends JpaRepository<Customer, Long> {
}
