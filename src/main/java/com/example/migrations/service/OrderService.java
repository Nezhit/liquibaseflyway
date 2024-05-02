package com.example.migrations.service;

import com.example.migrations.dto.OrderDto;
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

    public Order createOrder(OrderDto orderDto) {
        Order order = new Order(orderDto);
        return orderRepo.save(order);
    }

    public Order updateOrder(Long id, OrderDto orderDto) {
        Order order = orderRepo.findById(id).get();
        order.setAmount(orderDto.getAmount());
        order.setOrderDate(orderDto.getOrderDate());
        order.setArriveDate(orderDto.getArriveDate());
        order.setGood(orderDto.getGood());
        order.setEmployee(orderDto.getEmployee());
        order.setCustomer(orderDto.getCustomer());
        order.setPrice(orderDto.getPrice());
        return orderRepo.save(order);
    }

    public void deleteOrder(Long id) {
        Order order = orderRepo.findById(id).get();
        orderRepo.delete(order);
    }

    public Order getOrderById(Long id) {
        return orderRepo.findById(id).get();
    }
}
