package cosmonaut.service;

import cosmonaut.dto.MessageDto;
import cosmonaut.entity.Chat;
import cosmonaut.entity.Message;
import cosmonaut.entity.User;
import cosmonaut.repository.ChatRepo;
import cosmonaut.repository.MessageRepo;
import cosmonaut.util.CurrentUserUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {
    private final MessageRepo messageRepo;

    private final ChatRepo chatRepo;

    private final CurrentUserUtils currentUserUtils;

    private final UserService userService;

    public MessageService(MessageRepo messageRepo, ChatRepo chatRepo, CurrentUserUtils currentUserUtils, UserService userService) {
        this.messageRepo = messageRepo;
        this.chatRepo = chatRepo;
        this.currentUserUtils = currentUserUtils;
        this.userService = userService;
    }

    public ResponseEntity<?> saveMessage(MessageDto messageDto) {
        Message message = new Message();
        Chat chat = chatRepo.findChatById(messageDto.getChatId()).get();
        message.setText(messageDto.getMessage());
        message.setChat(chat);
        message.setTime(LocalDateTime.now());
        message.setFileUrl(messageDto.getFileUrl());
        message.setUser(currentUserUtils.getCurrentLoggedUser());
        messageRepo.save(message);
        return ResponseEntity.ok(")");
    }

    public Page<Message> getMessagesForChat(Long chatId, Pageable pageable) {
        Chat chat = chatRepo.findChatById(chatId).get();
        List<Message> messages = messageRepo.findByChat(chat);
        return messageRepo.findByChatOrderByTime(chat, pageable);
    }

    public Page<Message> getMessagesForChatDesc(Long chatId, Pageable pageable) {
        Chat chat = chatRepo.findChatById(chatId).get();
        List<Message> messages = messageRepo.findByChat(chat);
        return messageRepo.findByChatOrderByTimeDesc(chat, pageable);
    }

    public Page<Message> getMessagesForUser(User user, Pageable pageable) {
        return messageRepo.findByUser(user, pageable);
    }

    public Page<Message> getMessagesForUserBetweenDates(LocalDateTime start,
                                                        LocalDateTime end,
                                                        Pageable pageable) {
        return messageRepo.findByUserAndTimeBetween(currentUserUtils.getCurrentLoggedUser().getUsername(),
                start, end, pageable);
    }

    public String getFileName(MultipartFile file) {
        String uploadDir = "avatars";
        if (file != null && !file.isEmpty()) {
            return StringUtils.cleanPath(file.getOriginalFilename());
        } else {
            throw new RuntimeException("File is empty or null");
        }
    }
}
