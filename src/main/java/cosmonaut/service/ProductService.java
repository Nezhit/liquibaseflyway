package cosmonaut.service;

import cosmonaut.entity.Product;
import cosmonaut.entity.User;
import cosmonaut.entity.enums.UserRole;
import cosmonaut.repository.ProductRepository;
import cosmonaut.util.CurrentUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ProductService {

    private ProductRepository productRepository;

    private CurrentUserUtils currentUserUtils;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) { this.productRepository = productRepository; }

    @Autowired
    public void setCurrentUserUtils(CurrentUserUtils currentUserUtils) {
        this.currentUserUtils = currentUserUtils;
    }

    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product getProductById(Long id) {
        if (productRepository.findById(id).isPresent()) {
            return productRepository.findById(id).get();
        }
        return null;
    }

    public void deleteProductById(Long id) {
        User user = currentUserUtils.getCurrentLoggedUser();
        if(user == null) return;
        UserRole role = user.getRole();
        if (role == UserRole.ADMIN) {
            productRepository.deleteById(id);
        }
    }
}
