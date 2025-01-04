package org.example.todo.user.dto.response;

import org.example.todo.user.entity.User;


public record UserResponseDto(String name, String email) {

    public static UserResponseDto toDto(User user) {
        return new UserResponseDto(user.getName(), user.getEmail());
    }
}
