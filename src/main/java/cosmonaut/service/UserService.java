package cosmonaut.service;

import cosmonaut.entity.User;
import cosmonaut.repository.UserRepository;
import cosmonaut.util.CurrentUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import java.io.IOException;

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
        User user=findByUsername(currentUserUtils.getCurrentLoggedUser().getUsername());

        user.setAvatarUrl(fileStorageService.storeFile(multipartFile));
        userRepository.save(user);
        entityManager.clear();
        // Получаем обновленные данные пользователя из базы данных
        User updatedUser = findByUsername(currentUserUtils.getCurrentLoggedUser().getUsername());

        // Обновляем информацию в CurrentUserUtils
        currentUserUtils.setCurrentLoggedUser(updatedUser);
        return user.getAvatarUrl();
    }

}
