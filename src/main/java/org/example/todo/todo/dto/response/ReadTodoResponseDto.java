package org.example.todo.todo.dto.response;

import org.example.todo.todo.entity.Todo;

import java.time.LocalDateTime;

public record ReadTodoResponseDto(Long todoId, String title, String contents, LocalDateTime createdAt,
                                  LocalDateTime updatedAt) {

    public static ReadTodoResponseDto toDto(Todo foundTodo) {
        return new ReadTodoResponseDto(
                foundTodo.getTodoId(),
                foundTodo.getTitle(),
                foundTodo.getContents(),
                foundTodo.getCreatedAt(),
                foundTodo.getUpdatedAt()
        );
    }
}
