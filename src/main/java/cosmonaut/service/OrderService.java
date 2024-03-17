package cosmonaut.service;

import cosmonaut.entity.*;
import cosmonaut.entity.enums.UserRole;
import cosmonaut.repository.OrderRepository;
import cosmonaut.util.CurrentUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    private UserService userService;

    private CurrentUserUtils currentUserUtils;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setCurrentUserUtils(CurrentUserUtils currentUserUtils) {
        this.currentUserUtils = currentUserUtils;
    }

    public Order createOrderFromItems(User user, List<OrderItem> orderItems) {
        Order order = new Order();
        order.setOrderItems(new ArrayList<>());
        order.setUser(user);
        order.setIsAccepted(false);
        orderItems.forEach(orderItem -> {
            order.getOrderItems().add(orderItem);
            orderItem.setOrder(order);
        });
        return orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        return orderRepository.getOne(id);
    }

    public List<Order> getOrderByUser(User user) {
        return orderRepository.findAllByUser(user);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public void acceptOrderById(Long id) {
        Order orderToAccept = orderRepository.findById(id).get();
        UserRole role = currentUserUtils.getCurrentLoggedUser().getRole();
        if (role == UserRole.MANAGER) {
            orderToAccept.setIsAccepted(true);
            orderRepository.save(orderToAccept);
        }
    }

    public void deleteOrderById(Long id) {
        Order orderToDelete = orderRepository.findById(id).get();
        if(!orderToDelete.getIsAccepted()) {
            orderRepository.deleteById(id);
        }
    }

    public List<Order> getCustomOrders() {
        User user = currentUserUtils.getCurrentLoggedUser();
        if(user == null) return Collections.emptyList();
        UserRole role = user.getRole();
        if (role == UserRole.ADMIN || role == UserRole.MANAGER) {
            return getAllOrders();
        }
        return getOrderByUser(user);
    }
}
