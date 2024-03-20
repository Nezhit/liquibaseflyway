package cosmonaut.service;

import cosmonaut.entity.User;
import cosmonaut.repository.UserRepository;
import cosmonaut.util.CurrentUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserService {

    private UserRepository userRepository;
    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private CurrentUserUtils currentUserUtils;

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
        return user.getAvatarUrl();
    }

}
