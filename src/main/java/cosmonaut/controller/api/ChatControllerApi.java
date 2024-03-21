package cosmonaut.controller.api;

import cosmonaut.dto.MessageDto;
import cosmonaut.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
}
