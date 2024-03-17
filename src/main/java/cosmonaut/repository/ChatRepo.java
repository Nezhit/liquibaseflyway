package cosmonaut.repository;

import cosmonaut.entity.Chat;
import cosmonaut.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepo extends JpaRepository<Chat,Long> {
    List<Chat> findChatsBySender(User sender);
    Optional<Chat> findChatBySenderAndReceiver(User sender,User receiver);
    @Query("SELECT c FROM Chat c WHERE :user1 MEMBER OF c.users AND :user2 MEMBER OF c.users")
    Optional<Chat> findChatByUsersContains(User user1, User user2);

}
