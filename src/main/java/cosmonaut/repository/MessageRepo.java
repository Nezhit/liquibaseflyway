package cosmonaut.repository;

import cosmonaut.entity.Chat;
import cosmonaut.entity.Message;
import cosmonaut.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepo extends JpaRepository<Message,Long> {
    List<Message> findByChat(Chat chat);
    Page<Message> findByChatOrderByTime(Chat chat, Pageable pageable);
    Page<Message> findByChatOrderByTimeDesc(Chat chat, Pageable pageable);
    Page<Message> findByUser(User user, Pageable pageable);
    Long countMessagesByUser(User user);

}
