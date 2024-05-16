package com.example.migrations.repository;

import com.example.migrations.entity.Customer;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CustomerRepo extends JpaRepository<Customer, Long> {
}
