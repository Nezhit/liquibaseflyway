package cosmonaut.entity;

import cosmonaut.entity.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
    @Column(name = "email")
    private String email;
    private String avatarUrl;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_profile_id", referencedColumnName = "id")
    private UserProfile userProfile;
    @ManyToMany(mappedBy = "users")
    private Set<Chat> chats; // Добавлено для двусторонней связи

    public User() {
    }

    public User(String username, String password, String email, UserProfile userProfile) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.userProfile = userProfile;
    }
}
