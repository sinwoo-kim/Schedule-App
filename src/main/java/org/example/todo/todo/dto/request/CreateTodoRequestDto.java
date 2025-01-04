package org.example.todo.todo.dto.request;

public record CreateTodoRequestDto(Long userId, String username, String title, String contents ) {
}
