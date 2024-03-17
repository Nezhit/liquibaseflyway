package cosmonaut.util;

import org.springframework.stereotype.Component;
import cosmonaut.entity.User;

import java.util.List;

@Component
public class CurrentUserUtils {
    private User currentLoggedUser = null;

    public User getCurrentLoggedUser() {
        return currentLoggedUser;
    }

    public void setCurrentLoggedUser(User loggedUser) {
        currentLoggedUser = loggedUser;
    }
}
