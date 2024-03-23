package cosmonaut.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface UserControllerApi {
    @PostMapping("/authenticateTheUser")
    String login(@RequestParam String username, @RequestParam String password, Model model);

    @GetMapping("/logout")
    String logout();

    @GetMapping("/register")
    String toRegisterPage();

    @PostMapping("/register")
    String register(@RequestParam String username, @RequestParam String password, @RequestParam String name,@RequestParam String email, @RequestParam String city);

    @PostMapping("/profile/uploadAvatar")
    ResponseEntity<Map<String, String>> uploadAvatar(@RequestParam("avatar") MultipartFile avatar) throws IOException;
    @GetMapping("/user-statistics")
    public String getUserStatistics(Model model);
}
