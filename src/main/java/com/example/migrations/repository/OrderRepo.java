package com.example.migrations.repository;

import com.example.migrations.entity.Order;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface OrderRepo extends JpaRepository<Order,Long> {
    List<Order> findAll();
}
