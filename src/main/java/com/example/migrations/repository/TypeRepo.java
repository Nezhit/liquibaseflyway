package com.example.migrations.repository;

import com.example.migrations.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepo extends JpaRepository<Type,Long> {
}
