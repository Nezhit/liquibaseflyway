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
        Chat chat = chatRepo.findChatBySenderAndReceiver(currentUserUtils.getCurrentLoggedUser(),userService.findByUsername(messageDto.getReceiver())).get();
        message.setText(messageDto.getMessage());
        message.setChat(chat);
        message.setTime(LocalDateTime.now());
        messageRepo.save(message);
        return ResponseEntity.ok(")");
    }

    public Page<Message> getMessagesForChat(User currentLoggedUser, User receiver, Pageable pageable) {
        Chat chat = chatRepo.findChatBySenderAndReceiver(currentLoggedUser,receiver).get();
        List<Message> messages = messageRepo.findByChat(chat);
        return messageRepo.findByChatSenderAndChatReceiverOrderByTime(currentLoggedUser, receiver, pageable);
    }
    public Page<Message> getMessagesForChatDesc(User currentLoggedUser, User receiver, Pageable pageable) {
        Chat chat = chatRepo.findChatBySenderAndReceiver(currentLoggedUser,receiver).get();
        List<Message> messages = messageRepo.findByChat(chat);
        return messageRepo.findByChatSenderAndChatReceiverOrderByTimeDesc(currentLoggedUser, receiver, pageable);
    }
}
