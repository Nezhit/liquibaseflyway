package cosmonaut.controller;

import cosmonaut.controller.api.ChatControllerApi;
import cosmonaut.dto.MessageDto;
import cosmonaut.entity.Message;
import cosmonaut.repository.UserRepository;
import cosmonaut.service.ChatService;
import cosmonaut.service.FileStorageService;
import cosmonaut.service.MessageService;
import cosmonaut.service.UserService;
import cosmonaut.util.CurrentUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
    FileStorageService fileStorageService;
    @Autowired
    public void setCurrentUserUtils(CurrentUserUtils currentUserUtils) {
        this.currentUserUtils = currentUserUtils;
    }

    @Override
    public String getChat(@PathVariable String username, Model model){
        model.addAttribute("chat",chatService.openChat(currentUserUtils.getCurrentLoggedUser(),userService.findByUsername(username)));
        model.addAttribute("currentUser",currentUserUtils.getCurrentLoggedUser());
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
    @Override

    public ResponseEntity<?> uploadMessage(@PathVariable Long chatId,
                                           @RequestParam("message") String message,
                                           @RequestParam(value = "file", required = false) MultipartFile file){
        String uploadDir = "avatars";
        String fileName = null;

        // Проверка на наличие файла в запросе
        if (file != null && !file.isEmpty()) {
            fileName = StringUtils.cleanPath(file.getOriginalFilename());

            try {
                fileStorageService.saveFile(uploadDir, fileName, file);
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not upload file: " + fileName);
            }
        }

        MessageDto messageDto=new MessageDto();
        messageDto.setChatId(chatId);
        messageDto.setMessage(message);
        messageDto.setFileUrl(fileName);
        messageService.saveMessage(messageDto);

        return ResponseEntity.ok("File uploaded successfully: " + fileName);
    }

    @Override
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(String filename, HttpServletRequest request) throws Exception {
        Resource resource = fileStorageService.loadFileAsResource(filename);

        if (resource == null) {
            return ResponseEntity.notFound().build();
        }

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            // logger.info("Could not determine file type.");
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
