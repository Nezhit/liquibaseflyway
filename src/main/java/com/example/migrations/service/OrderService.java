package com.example.migrations.service;

import com.example.migrations.dto.OrderDto;
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

    public Order createOrder(OrderDto orderDto) {
        Order order = new Order(orderDto);
        return orderRepo.save(order);
    }

    public Order updateOrder(Long id, OrderUpdateDto orderUpdateDto) {
        Order order = orderRepo.findById(id).get();
        if(orderUpdateDto.getAmount() !=null) order.setAmount(orderUpdateDto.getAmount());
        if(orderUpdateDto.getOrderDate() !=null) order.setOrderDate(orderUpdateDto.getOrderDate());
        if(orderUpdateDto.getArriveDate() !=null) order.setArriveDate(orderUpdateDto.getArriveDate());
        if(orderUpdateDto.getGood() !=null) order.setGood(orderUpdateDto.getGood());
        if(orderUpdateDto.getEmployee() !=null) order.setEmployee(orderUpdateDto.getEmployee());
        if(orderUpdateDto.getCustomer() !=null) order.setCustomer(orderUpdateDto.getCustomer());
        if(orderUpdateDto.getPrice() !=null) order.setPrice(orderUpdateDto.getPrice());
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
