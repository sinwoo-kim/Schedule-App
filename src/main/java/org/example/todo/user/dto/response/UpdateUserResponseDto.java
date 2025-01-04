package org.example.todo.user.dto.response;

import org.example.todo.user.entity.User;

public record UpdateUserResponseDto(String name) {

    public static UpdateUserResponseDto toDto(User user) {
        return new UpdateUserResponseDto(user.getName());
    }
}
