package org.example.todo.auth.dto.request;

public record SignUpRequestDto(
        String email,
        String password,
        String name
) {
}
