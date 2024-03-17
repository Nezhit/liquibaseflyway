package cosmonaut.controller;

import cosmonaut.entity.User;
import cosmonaut.entity.UserProfile;
import cosmonaut.entity.enums.UserRole;
import cosmonaut.repository.UserRepository;
import cosmonaut.util.CurrentUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private UserRepository userRepository;
    private CurrentUserUtils currentUserUtils;

    @Autowired
    public void setUserRepository(UserRepository userRepository) { this.userRepository = userRepository; }

    @Autowired
    public void setCurrentUserUtils(CurrentUserUtils currentUserUtils) { this.currentUserUtils = currentUserUtils; }

    @PostMapping("/authenticateTheUser")
    public String login(@RequestParam String username, @RequestParam String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user != null) {
            currentUserUtils.setCurrentLoggedUser(user);
            return "index";
        }
        else return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        currentUserUtils.setCurrentLoggedUser(null);
        return "index";
    }

    @GetMapping("/register")
    public String toRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password,
                           @RequestParam String name, @RequestParam String city) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user == null) {
            userRepository.save(new User(username, password, UserRole.USER, new UserProfile(name, city)));
            return "login";
        }
        else return "register";
    }
}
