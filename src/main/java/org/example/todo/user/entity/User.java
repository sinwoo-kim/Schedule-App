package org.example.todo.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.example.todo.common.BaseEntity;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "user")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @CreatedDate
    private LocalDateTime createAt;

    private User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    private User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    public static User create(String email, String password, String name) {
        return new User(email, password, name);
    }

    public void update(String username, String email) {
        this.name = username;
        this.email = email;
    }
}
