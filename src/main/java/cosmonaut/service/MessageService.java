package cosmonaut.service;

import cosmonaut.dto.MessageDto;
import cosmonaut.entity.Chat;
import cosmonaut.entity.Message;
import cosmonaut.entity.User;
import cosmonaut.repository.ChatRepo;
import cosmonaut.repository.MessageRepo;
import cosmonaut.util.CurrentUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class MessageService {
    @Autowired
    MessageRepo messageRepo;
    @Autowired
    ChatRepo chatRepo;
    @Autowired
    CurrentUserUtils currentUserUtils;
    @Autowired
    UserService userService;
    public ResponseEntity<?> saveMessage(MessageDto messageDto){
        Message message=new Message();
        //Chat chat = chatRepo.findChatByUsers(messageDto.getUsers()).get();
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
    public Page<Message> getMessagesForUser(User user, Pageable pageable){
        return messageRepo.findByUser(user,pageable);
    }
}
