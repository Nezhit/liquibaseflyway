package cosmonaut.service;

import cosmonaut.entity.Chat;
import cosmonaut.entity.User;
import cosmonaut.repository.ChatRepo;
import cosmonaut.repository.MessageRepo;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

@Service
public class ChatService {
    private final MessageRepo messageRepo;
    private final ChatRepo chatRepo;

    public ChatService(MessageRepo messageRepo, ChatRepo chatRepo) {
        this.messageRepo = messageRepo;
        this.chatRepo = chatRepo;
    }

    public Chat openChat(User user1, User user2) {
        Optional<Chat> chatOptional = chatRepo.findChatByUsersContains(user1, user2);
        if (chatOptional.isPresent()) {
            return chatOptional.get();
        } else {
            Chat chat = new Chat();
            chat.setUsers(new HashSet<>(Arrays.asList(user1, user2)));
            return chatRepo.save(chat);
        }
    }
}
