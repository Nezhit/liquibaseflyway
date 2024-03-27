package cosmonaut.controller.api;

import cosmonaut.dto.MessageDto;
import cosmonaut.entity.Message;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface ChatControllerApi {
    @GetMapping("/openchat/{username}")
    String getChat(@PathVariable String username, Model model);

    @PostMapping("/sendMessage")
    ResponseEntity<?> receiveMessage(@RequestBody MessageDto messageDto);

    @GetMapping("/updateChat/{chatId}")
    @ResponseBody
    Page<Message> updateChat(@PathVariable Long chatId, Pageable pageable);

    @GetMapping("/updateChatDesc/{chatId}")
    @ResponseBody
    Page<Message> updateChatDesc(@PathVariable Long chatId, Pageable pageable);
    @GetMapping("/getMessagesForUserBetweenDates")
    @ResponseBody
    Page<Message> getMessagesForUserBetweenDates( @RequestParam String startTime,
                                                  @RequestParam String endTime,Pageable pageable)  ;




    @PostMapping("/chats/{chatId}/messages/upload")
    public ResponseEntity<?> uploadMessage(@PathVariable Long chatId,
                                           @RequestParam("message") String message,
                                           @RequestParam(value = "file", required = false) MultipartFile file) throws IOException;

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename, HttpServletRequest request) throws Exception;
}
