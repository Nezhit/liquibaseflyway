package cosmonaut.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserStatisticDTO {
    private String username;
    private long messageCount;

    public UserStatisticDTO(String username, long messageCount) {
        this.username = username;
        this.messageCount = messageCount;
    }
}
