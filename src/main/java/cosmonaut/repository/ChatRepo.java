package cosmonaut.repository;

import cosmonaut.entity.Chat;
import cosmonaut.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ChatRepo extends JpaRepository<Chat, Long> {

    @Query("SELECT c FROM Chat c JOIN c.users u WHERE u IN :users GROUP BY c HAVING COUNT(u) = :#{#users.size}")
    Optional<Chat> findChatByUsers(@Param("users") Set<User> users);

    @Query("SELECT c FROM Chat c WHERE :user1 MEMBER OF c.users AND :user2 MEMBER OF c.users")
    Optional<Chat> findChatByUsersContains(User user1, User user2);

    Optional<Chat> findChatById(Long chatId);
}
