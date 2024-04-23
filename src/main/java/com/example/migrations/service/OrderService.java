package com.example.migrations.service;

import com.example.migrations.dto.OrderDto;
import com.example.migrations.entity.Order;
import com.example.migrations.repository.GoodRepo;
import com.example.migrations.repository.OrderRepo;
import org.springframework.http.ResponseEntity;
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

    public Order createOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setAmount(orderDto.getAmount());
        order.setOrderDate(orderDto.getOrderDate());
        order.setArriveDate(orderDto.getArriveDate());
        order.setGood(orderDto.getGood());
        order.setEmployee(orderDto.getEmployee());
        order.setCustomer(orderDto.getCustomer());
        order.setPrice(orderDto.getPrice());
        orderRepo.save(order);
        return order;
    }

    public Order updateOrder(Long id,OrderDto orderDto) {
        Order order=orderRepo.findById(id).get();
        order.setAmount(orderDto.getAmount());
        order.setOrderDate(orderDto.getOrderDate());
        order.setArriveDate(orderDto.getArriveDate());
        order.setGood(orderDto.getGood());
        order.setEmployee(orderDto.getEmployee());
        order.setCustomer(orderDto.getCustomer());
        order.setPrice(orderDto.getPrice());
        orderRepo.save(order);
        return order;
    }

    public Order deleteOrder(Long id) {
        Order order=orderRepo.findById(id).get();
        orderRepo.delete(order);
        return order;
    }
}
