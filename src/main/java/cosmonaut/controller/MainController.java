package cosmonaut.controller;

import cosmonaut.controller.api.MainControllerApi;
import cosmonaut.repository.UserRepository;
import cosmonaut.service.FileStorageService;
import cosmonaut.util.CurrentUserUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController implements MainControllerApi {
    private final UserRepository userRepository;
    private final FileStorageService fileStorageService;
    private final CurrentUserUtils currentUserUtils;

    public MainController(UserRepository userRepository, FileStorageService fileStorageService, CurrentUserUtils currentUserUtils) {
        this.userRepository = userRepository;
        this.fileStorageService = fileStorageService;
        this.currentUserUtils = currentUserUtils;
    }


    @Override
    public String toHomepage() {
        return "index";
    }

    @Override
    public String showAllUsers(Model model, Pageable pageable) {
        model.addAttribute("users",
                userRepository.findByUsernameNot(currentUserUtils.getCurrentLoggedUser().getUsername(),
                        pageable));
        return "users";
    }

    @Override
    public String toLoginPage() {
        return "login";
    }

    @Override
    public String getPersonPage(Model model) {
        if (currentUserUtils.getCurrentLoggedUser() == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", currentUserUtils.getCurrentLoggedUser());
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
