package cosmonaut.util;

import cosmonaut.entity.User;
import org.springframework.stereotype.Component;

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
