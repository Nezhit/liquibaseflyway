package cosmonaut.controller.api;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface MainControllerApi {
    @GetMapping("/index")
    String toHomepage();

    @GetMapping("/users")
    String showAllUsers(Model model, Pageable pageable);

    @GetMapping("/login")
    String toLoginPage();

    @GetMapping("/personpage")
    String getPersonPage(Model model);

    @GetMapping("/getAvatar/{imageName}")
    ResponseEntity<byte[]> getImage(@PathVariable String imageName);
}
