package cosmonaut.service;

import cosmonaut.entity.Chat;
import cosmonaut.entity.User;
import cosmonaut.repository.ChatRepo;
import cosmonaut.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

@Service
public class ChatService {
    @Autowired
    MessageRepo messageRepo;
    @Autowired
    ChatRepo chatRepo;
    public Chat openChat(User user1, User user2) {
        // Попытка найти чат с участием обоих пользователей
        Optional<Chat> chatOptional = chatRepo.findChatByUsersContains(user1, user2);

        if (chatOptional.isPresent()) {
            return chatOptional.get();
        } else {
            Chat chat = new Chat();
            chat.setUsers(new HashSet<>(Arrays.asList(user1, user2))); // Создание чата с двумя пользователями
            return chatRepo.save(chat);
        }
    }

}
