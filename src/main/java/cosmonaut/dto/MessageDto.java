package cosmonaut.dto;

import cosmonaut.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MessageDto {
    private String message;
    private String receiver;
}
