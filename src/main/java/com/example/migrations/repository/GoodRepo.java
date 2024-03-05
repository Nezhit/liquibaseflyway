package com.example.migrations.repository;

import com.example.migrations.entity.Good;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodRepo extends JpaRepository<Good,Long> {
}
