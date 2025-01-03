package org.example.todo.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.todo.common.BaseEntity;
import org.example.todo.user.dto.request.LoginRequestDto;
import org.example.todo.user.dto.request.SignUpRequestDto;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
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
    private String username;

    @CreatedDate
    private LocalDateTime createAt;

    private User(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }

    private User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    // signUp dto -> User Entity
    public static User createFromSignUpDto(SignUpRequestDto signUpRequestDto) {
        return new User(signUpRequestDto.getEmail(), signUpRequestDto.getPassword(), signUpRequestDto.getUsername());
    }

    // login dto -> User Entity
    public static User toEntityFromLoginRequestDto(LoginRequestDto loginRequestDto) {
        return new User(loginRequestDto.getEmail(), loginRequestDto.getPassword());
    }

}
