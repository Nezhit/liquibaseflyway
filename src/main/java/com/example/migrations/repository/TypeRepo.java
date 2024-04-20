package com.example.migrations.repository;

import com.example.migrations.entity.Type;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
@Tag(name = "Type Repository", description = "Repository extending Jpa Repository for Spring data jpa")
public interface TypeRepo extends JpaRepository<Type,Long> {
}
