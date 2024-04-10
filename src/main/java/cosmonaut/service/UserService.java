package cosmonaut.service;

import cosmonaut.dto.UserStatisticDTO;
import cosmonaut.entity.User;
import cosmonaut.entity.UserProfile;
import cosmonaut.repository.UserRepository;
import cosmonaut.util.CurrentUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private CurrentUserUtils currentUserUtils;
    @Autowired
    EntityManager entityManager;

    public UserService() {
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsername(String username) {
        if (userRepository.findById(username).isPresent()) {
            return userRepository.findById(username).get();
        }
        return null;
    }

    public String uploadAvatar(MultipartFile multipartFile) throws IOException {
        User user = findByUsername(currentUserUtils.getCurrentLoggedUser().getUsername());

        user.setAvatarUrl(fileStorageService.storeFile(multipartFile));
        userRepository.save(user);
        entityManager.clear();
        // Получаем обновленные данные пользователя из базы данных
        User updatedUser = findByUsername(currentUserUtils.getCurrentLoggedUser().getUsername());

        // Обновляем информацию в CurrentUserUtils
        currentUserUtils.setCurrentLoggedUser(updatedUser);
        return user.getAvatarUrl();
    }

    public List<UserStatisticDTO> getUserStatistics() {
        return userRepository.findUserStatistics();
    }


    public String checkUserAndPutToModel(User user, Model model) {
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

    public String registerLogic(String username, String password, String name, String email, String city) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user == null) {
            userRepository.save(new User(username, password, email, new UserProfile(name, city)));
            return "login";
        } else return "register";
    }
}
