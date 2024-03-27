package cosmonaut.controller;

import cosmonaut.controller.api.MainControllerApi;
import cosmonaut.repository.UserRepository;
import cosmonaut.service.FileStorageService;
import cosmonaut.service.UserService;
import cosmonaut.util.CurrentUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class MainController implements MainControllerApi {

    private UserRepository userRepository;
    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private CurrentUserUtils currentUserUtils;
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public String toHomepage() {
        return "index";
    }
    @Override
    public String showAllUsers(Model model, Pageable pageable) {
        model.addAttribute("users", userRepository.findByUsernameNot(currentUserUtils.getCurrentLoggedUser().getUsername(),pageable));
        return "users";
    }
    @Override
    public String toLoginPage() {
        return "login";
    }
    @Override
    public String getPersonPage(Model model){
        if(currentUserUtils.getCurrentLoggedUser() == null){
            return "redirect:/login";
        }
        model.addAttribute("user",currentUserUtils.getCurrentLoggedUser());
        return "personpage";
    }

    @Override
    public String getSentMessagesPage() {
        return "sentmessages";
    }

    @Override
    public ResponseEntity<byte[]> getImage(@RequestParam(value = "t", required = false) Long timestamp) {
        return fileStorageService.getImage();
    }
}
