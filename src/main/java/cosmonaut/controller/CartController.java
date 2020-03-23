package cosmonaut.controller;

import cosmonaut.entity.Order;
import cosmonaut.entity.OrderItem;
import cosmonaut.entity.User;
import cosmonaut.service.OrderService;
import cosmonaut.service.UserService;
import cosmonaut.util.ShoppingCart;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    private ShoppingCart cart;

    private OrderService orderService;

    private UserService userService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String showCart(Model model) {
        model.addAttribute("orderItems", cart.getOrderItems());
        return "cart";
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable("id") Long id) {
        cart.addProductById(id);
        return "redirect:/shop";
    }


}
