package com.example.migrations.repository;

import com.example.migrations.entity.Good;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
@Tag(name = "Good Repository", description = "Repository extending Jpa Repository for Spring data jpa")
public interface GoodRepo extends JpaRepository<Good,Long> {
}
