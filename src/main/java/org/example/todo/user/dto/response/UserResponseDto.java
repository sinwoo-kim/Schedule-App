package org.example.todo.user.dto.response;

import lombok.Getter;
import org.example.todo.user.entity.User;


@Getter
public record UserResponseDto(String name, String email) {

    public static UserResponseDto toDto(User user) {
        return new UserResponseDto(user.getName(), user.getEmail());
    }
}
