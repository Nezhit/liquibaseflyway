package cosmonaut.util;

import cosmonaut.entity.OrderItem;
import cosmonaut.entity.Product;
import cosmonaut.entity.User;
import cosmonaut.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCart {

    private Map<String, List<OrderItem>> orderItems;

    private ProductService productService;

    private CurrentUserUtils currentUserUtils;

    public List<OrderItem> getOrderItems() {
        if(currentUserUtils.getCurrentLoggedUser() == null) return Collections.emptyList();
        List<OrderItem> userOrderItems = orderItems.get(currentUserUtils.getCurrentLoggedUser().getUsername());
        if (userOrderItems == null) userOrderItems = Collections.emptyList();
        return userOrderItems;
    }

    public void clearUserCart() {
        User currUser = currentUserUtils.getCurrentLoggedUser();
        if(currUser != null) {
            orderItems.put(currUser.getUsername(), new ArrayList<>());
        }
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setCurrentUserUtils(CurrentUserUtils currentUserUtils) {
        this.currentUserUtils = currentUserUtils;
    }

    @PostConstruct
    public void init() {
        orderItems = new HashMap<>();
    }

    public void addProductById(Long id) {
        User currUser = currentUserUtils.getCurrentLoggedUser();
        if (currUser == null) return;
        Product product = productService.getProductById(id);
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);

        List<OrderItem> orderItemsList = orderItems.get(currUser.getUsername());
        if(orderItemsList != null) {
            orderItemsList.add(orderItem);
        } else {
            orderItemsList = new ArrayList<>();
            orderItemsList.add(orderItem);
        }
        orderItems.put(currUser.getUsername(), orderItemsList);
    }
}
