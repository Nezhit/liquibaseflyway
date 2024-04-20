package com.example.migrations.repository;

import com.example.migrations.entity.Producer;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
@Tag(name = "Producer Repository", description = "Repository extending Jpa Repository for Spring data jpa")
public interface ProducerRepo extends JpaRepository<Producer,Long> {
}
