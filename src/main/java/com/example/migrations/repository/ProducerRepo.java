package com.example.migrations.repository;

import com.example.migrations.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProducerRepo extends JpaRepository<Producer,Long> {
}
