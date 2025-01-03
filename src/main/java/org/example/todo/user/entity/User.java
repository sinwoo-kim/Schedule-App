package org.example.todo.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.example.todo.auth.dto.request.SignUpRequestDto;
import org.example.todo.common.BaseEntity;
import org.example.todo.user.dto.request.LoginRequestDto;
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

    // signUp dto -> User Entity
    public static User createFromSignUpDto(SignUpRequestDto signUpRequestDto) {
        return new User(signUpRequestDto.email(), signUpRequestDto.password(), signUpRequestDto.name());
    }

    // login dto -> User Entity
    public static User toEntityFromLoginRequestDto(LoginRequestDto loginRequestDto) {
        return new User(loginRequestDto.getEmail(), loginRequestDto.getPassword());
    }

    public void update(String username, String email) {
        this.name = username;
        this.email = email;
    }
}
