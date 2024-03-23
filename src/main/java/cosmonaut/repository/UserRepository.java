package cosmonaut.repository;

import cosmonaut.dto.UserStatisticDTO;
import cosmonaut.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUsernameAndPassword(String username, String password);
    Page findByUsernameNot(String username, Pageable pageable);
    @Query("SELECT new cosmonaut.dto.UserStatisticDTO(m.user.username, COUNT(m)) " +
            "FROM Message m " +
            "GROUP BY m.user.username " +
            "ORDER BY COUNT(m) DESC")
    List<UserStatisticDTO> findUserStatistics();
}
