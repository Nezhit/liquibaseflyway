package cosmonaut.controller;

import cosmonaut.controller.api.ChatControllerApi;
import cosmonaut.dto.MessageDto;
import cosmonaut.entity.Message;
import cosmonaut.repository.UserRepository;
import cosmonaut.service.ChatService;
import cosmonaut.service.MessageService;
import cosmonaut.service.UserService;
import cosmonaut.util.CurrentUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ChatController implements ChatControllerApi {
    private CurrentUserUtils currentUserUtils;
    @Autowired
    private ChatService chatService;

    @Autowired
    UserService userService;
    @Autowired
    MessageService messageService;
    @Autowired
    public void setCurrentUserUtils(CurrentUserUtils currentUserUtils) {
        this.currentUserUtils = currentUserUtils;
    }

    @Override
    public String getChat(@PathVariable String username, Model model){
        model.addAttribute("chat",chatService.openChat(currentUserUtils.getCurrentLoggedUser(),userService.findByUsername(username)));
        return "chatpage";
    }
    @Override

    public ResponseEntity<?> receiveMessage(@RequestBody MessageDto messageDto){
       return messageService.saveMessage(messageDto);
    }
    @Override

    public Page<Message> updateChat(@PathVariable Long chatId, Pageable pageable) {

        Page<Message> messages = messageService.getMessagesForChat(chatId,pageable);

        return messages;
    }
    @Override

    public Page<Message> updateChatDesc(@PathVariable Long chatId, Pageable pageable) {

        Page<Message> messages = messageService.getMessagesForChatDesc(chatId,pageable);

        return messages;
    }
}
