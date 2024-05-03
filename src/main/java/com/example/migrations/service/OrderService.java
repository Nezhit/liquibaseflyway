package com.example.migrations.service;

import com.example.migrations.dto.OrderCreateDto;
import com.example.migrations.dto.OrderRsDto;
import com.example.migrations.dto.OrderUpdateDto;
import com.example.migrations.entity.Order;
import com.example.migrations.repository.OrderRepo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepo orderRepo;

    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public List<Order> getOrders() {
        return orderRepo.findAll();
    }

    public OrderRsDto createOrder(OrderCreateDto orderCreateDto) {
        Order order = new Order(orderCreateDto);
        return new OrderRsDto(orderRepo.save(order));
    }

    public OrderRsDto updateOrder(Long id, OrderUpdateDto orderUpdateDto) {
        Order order = orderRepo.findById(id).orElseThrow(() -> new RuntimeException("Заказ не найден"));
        if (orderUpdateDto.getAmount() != null) order.setAmount(orderUpdateDto.getAmount());
        if (orderUpdateDto.getOrderDate() != null) order.setOrderDate(orderUpdateDto.getOrderDate());
        if (orderUpdateDto.getArriveDate() != null) order.setArriveDate(orderUpdateDto.getArriveDate());
        if (orderUpdateDto.getGood() != null) order.setGood(orderUpdateDto.getGood());
        if (orderUpdateDto.getEmployee() != null) order.setEmployee(orderUpdateDto.getEmployee());
        if (orderUpdateDto.getCustomer() != null) order.setCustomer(orderUpdateDto.getCustomer());
        if (orderUpdateDto.getPrice() != null) order.setPrice(orderUpdateDto.getPrice());
        return new OrderRsDto(orderRepo.save(order));
    }

    public void deleteOrder(Long id) {
        Order order = orderRepo.findById(id).orElseThrow(() -> new RuntimeException("Заказ не найден"));
        orderRepo.delete(order);
    }

    public OrderRsDto getOrderById(Long id) {
        return new OrderRsDto(orderRepo.findById(id).orElseThrow(() -> new RuntimeException("Заказ не найден")));
    }
}
