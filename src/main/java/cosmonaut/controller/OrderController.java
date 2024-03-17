package cosmonaut.controller;

import cosmonaut.entity.Order;
import cosmonaut.entity.User;
import cosmonaut.service.OrderService;
import cosmonaut.service.UserService;
import cosmonaut.util.CurrentUserUtils;
import cosmonaut.util.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    private UserService userService;

    private ShoppingCart cart;

    private CurrentUserUtils currentUserUtils;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setCurrentUserUtils(CurrentUserUtils currentUserUtils) {
        this.currentUserUtils = currentUserUtils;
    }

    @GetMapping("")
    public String showOrders(Model model) {
        User user = currentUserUtils.getCurrentLoggedUser();
        model.addAttribute("user", user);
        model.addAttribute("orders", orderService.getCustomOrders());
        return "orders";
    }

    @GetMapping("/order-details/{id}")
    public String toOrderDetails(Model model, @PathVariable("id") Long id) {
        Order selectedOrder = orderService.getOrderById(id);
        model.addAttribute("selectedOrder", selectedOrder);
        return "order-details";
    }

    @GetMapping("/create_order")
    public String createOrder() {
        User user = currentUserUtils.getCurrentLoggedUser();
        Order orderFromItems = orderService.createOrderFromItems(user, cart.getOrderItems());
        cart.clearUserCart();
        return "redirect:/orders/order-details/" + orderFromItems.getId();
    }

    @GetMapping("/accept/{id}")
    public String acceptOrderById(@PathVariable("id") Long id) {
        orderService.acceptOrderById(id);
        return "redirect:/orders";
    }

    @GetMapping("/remove/{id}")
    public String deleteOrderById(@PathVariable("id") Long id) {
        orderService.deleteOrderById(id);
        return "redirect:/orders";
    }
}
