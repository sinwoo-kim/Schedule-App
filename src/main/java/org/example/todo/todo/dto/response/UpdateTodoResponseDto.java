package org.example.todo.todo.dto.response;

import org.example.todo.todo.entity.Todo;

import java.time.LocalDateTime;

public record UpdateTodoResponseDto(Long todoId, String username, String title, String contents,
                                    LocalDateTime updatedAt) {

    public static UpdateTodoResponseDto toDto(Todo todo) {
        return new UpdateTodoResponseDto(
                todo.getTodoId(),
                todo.getUsername(),
                todo.getTitle(),
                todo.getContents(),
                todo.getUpdatedAt()
        );
    }
}
