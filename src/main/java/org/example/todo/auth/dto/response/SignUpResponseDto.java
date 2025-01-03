package org.example.todo.auth.dto.response;

import org.example.todo.user.entity.User;

public record SignUpResponseDto(
        String email,
        String name
) {
    public static SignUpResponseDto toDto(User savedUser) {
        return new SignUpResponseDto(
                savedUser.getEmail(),
                savedUser.getName()
        );
    }
}
