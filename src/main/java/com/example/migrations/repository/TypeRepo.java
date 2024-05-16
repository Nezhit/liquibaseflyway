package com.example.migrations.repository;

import com.example.migrations.entity.Type;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TypeRepo extends JpaRepository<Type,Long> {
}
