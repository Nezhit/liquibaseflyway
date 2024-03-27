package cosmonaut.dto;

import cosmonaut.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor

@Getter
@Setter
public class MessageDto {
    private String message;
    private Set<User> users;
    private String fileUrl;
    private Long chatId;

    public MessageDto(String message, String fileUrl, Long chatId) {
        this.message = message;

        this.fileUrl = fileUrl;
        this.chatId = chatId;
    }
}
