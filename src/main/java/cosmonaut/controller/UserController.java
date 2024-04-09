package cosmonaut.controller;

import cosmonaut.controller.api.UserControllerApi;
import cosmonaut.dto.UserStatisticDTO;
import cosmonaut.entity.User;
import cosmonaut.entity.UserProfile;
import cosmonaut.entity.enums.UserRole;
import cosmonaut.repository.UserRepository;
import cosmonaut.service.UserService;
import cosmonaut.util.CurrentUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController implements UserControllerApi {

    private UserRepository userRepository;
    private CurrentUserUtils currentUserUtils;
    @Autowired
    private UserService userService;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setCurrentUserUtils(CurrentUserUtils currentUserUtils) {
        this.currentUserUtils = currentUserUtils;
    }

    @Override
    @PostMapping("/authenticateTheUser")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user != null) {
            currentUserUtils.setCurrentLoggedUser(user);
            // Добавление URL аватарки в модель, если он доступен
            if (user.getAvatarUrl() != null && !user.getAvatarUrl().isEmpty()) {
                model.addAttribute("currentUser", user);
                model.addAttribute("avatarUrl", user.getAvatarUrl());
            }
            return "index";
        } else return "login";
    }

    @Override
    @GetMapping("/logout")
    public String logout() {
        currentUserUtils.setCurrentLoggedUser(null);
        return "index";
    }

    @Override
    @GetMapping("/register")
    public String toRegisterPage() {
        return "register";
    }

    @Override
    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String name,
                           @RequestParam String email,
                           @RequestParam String city) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user == null) {
            userRepository.save(new User(username, password, email, new UserProfile(name, city)));
            return "login";
        } else return "register";
    }

    @Override
    @PostMapping("/profile/uploadAvatar")
    public ResponseEntity<Map<String, String>> uploadAvatar(@RequestParam("avatar") MultipartFile avatar) throws IOException {
        String avatarUrl = userService.uploadAvatar(avatar);
        Map<String, String> response = new HashMap<>();
        response.put("avatarUrl", avatarUrl);
        return ResponseEntity.ok(response);
    }

    @Override
    public String getUserStatistics(Model model) {
        List<UserStatisticDTO> userStatistics = userService.getUserStatistics();
        model.addAttribute("userStatistics", userStatistics);
        return "userStatistics";
    }

}
