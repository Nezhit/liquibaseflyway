package cosmonaut.entity;

import cosmonaut.entity.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_profile_id", referencedColumnName = "id")
    private UserProfile userProfile;
    @ManyToMany(mappedBy = "users")
    private Set<Chat> chats; // Добавлено для двусторонней связи
    public User() {}

    public User(String username, String password, UserRole role, UserProfile userProfile) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.userProfile = userProfile;
    }


}
