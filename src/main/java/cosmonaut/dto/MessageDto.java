package cosmonaut.dto;

import cosmonaut.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class MessageDto {
    private String message;
    private Set<User> users;
    private Long chatId;
}
