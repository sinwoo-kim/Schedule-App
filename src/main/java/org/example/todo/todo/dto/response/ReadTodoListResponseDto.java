package org.example.todo.todo.dto.response;

import org.example.todo.todo.entity.Todo;

import java.time.LocalDateTime;

public record ReadTodoListResponseDto(Long todoId, String title, String contents, LocalDateTime createdAt,
                                      LocalDateTime updatedAt) {


    public static ReadTodoListResponseDto toDto(Todo todo) {
        return new ReadTodoListResponseDto(
                todo.getTodoId(),
                todo.getTitle(),
                todo.getContents(),
                todo.getCreatedAt(),
                todo.getUpdatedAt()
        );
    }
}
