package com.example.migrations.repository;

import com.example.migrations.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CustomerRepo extends JpaRepository<Customer, Long> {
}
