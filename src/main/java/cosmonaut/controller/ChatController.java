package cosmonaut.controller;

import cosmonaut.controller.api.ChatControllerApi;
import cosmonaut.dto.MessageDto;
import cosmonaut.entity.Message;
import cosmonaut.service.ChatService;
import cosmonaut.service.FileStorageService;
import cosmonaut.service.MessageService;
import cosmonaut.service.UserService;
import cosmonaut.util.CurrentUserUtils;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class ChatController implements ChatControllerApi {
    private final CurrentUserUtils currentUserUtils;
    private final ChatService chatService;
    private final UserService userService;
    private final MessageService messageService;
    private final FileStorageService fileStorageService;

    public ChatController(CurrentUserUtils currentUserUtils, ChatService chatService, UserService userService, MessageService messageService, FileStorageService fileStorageService) {
        this.currentUserUtils = currentUserUtils;
        this.chatService = chatService;
        this.userService = userService;
        this.messageService = messageService;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public String getChat(@PathVariable String username, Model model) {
        model.addAttribute("chat", chatService.openChat(currentUserUtils.getCurrentLoggedUser(), userService.findByUsername(username)));
        model.addAttribute("currentUser", currentUserUtils.getCurrentLoggedUser());
        return "chatpage";
    }

    @Override
    public ResponseEntity<?> receiveMessage(@RequestBody MessageDto messageDto) {
        return messageService.saveMessage(messageDto);
    }

    @Override
    public Page<Message> updateChat(@PathVariable Long chatId, Pageable pageable) {
        Page<Message> messages = messageService.getMessagesForChat(chatId, pageable);
        return messages;
    }

    @Override
    public Page<Message> updateChatDesc(@PathVariable Long chatId, Pageable pageable) {
        Page<Message> messages = messageService.getMessagesForChatDesc(chatId, pageable);
        return messages;
    }

    @Override
    public Page<Message> getMessagesForUserBetweenDates(String startTime, String endTime, Pageable pageable) {
        LocalDateTime start = LocalDateTime.parse(startTime, DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime end = LocalDateTime.parse(endTime, DateTimeFormatter.ISO_DATE_TIME);
        return messageService.getMessagesForUserBetweenDates(start, end, pageable);
    }

    @Override
    public ResponseEntity<?> uploadMessage(@PathVariable Long chatId,
                                           @RequestParam("message") String message,
                                           @RequestParam(value = "file", required = false) MultipartFile file)
            throws IOException {
        MessageDto messageDto = new MessageDto(message, messageService.getFileName(file), chatId);
        messageService.saveMessage(messageDto);
        return fileStorageService.saveFile("avatars", messageService.getFileName(file), file);
    }

    @Override
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(String filename, HttpServletRequest request)
            throws Exception {
        Resource resource = fileStorageService.loadFileAsResource(filename);
        return fileStorageService.checkResource(resource, request);

    }
}
