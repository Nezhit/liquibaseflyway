import cosmonaut.WebChatApp;
import cosmonaut.entity.User;
import cosmonaut.repository.UserRepository;
import cosmonaut.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = WebChatApp.class)
public class ServiceUnit {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
        userService.setUserRepository(userRepository);
    }

    @Test
    void findByUsername_UserExists_ReturnsUser() {
        User mockUser = new User();
        mockUser.setUsername("testUser");
        when(userRepository.findById("testUser")).thenReturn(Optional.of(mockUser));

        User found = userService.findByUsername("testUser");
        System.out.println(found.getUsername() + found.getEmail());
        assertNotNull(found);
        assertEquals("testUser", found.getUsername());
    }

    @Test
    void findByUsername_UserDoesNotExist_ReturnsNull() {
        when(userRepository.findById("unknownUser")).thenReturn(Optional.empty());

        User found = userService.findByUsername("unknownUser");

        assertNull(found);
    }
}
