package org.example.todo.todo.dto.response;

import org.example.todo.todo.entity.Todo;

import java.time.LocalDateTime;

public record ReadTodoResponseDto(Long todoId, String title, String contents, LocalDateTime createdAt,
                                  LocalDateTime modifiedAt) {

    public static ReadTodoResponseDto toDto(Todo todo) {
        return new ReadTodoResponseDto(
                todo.getTodoId(),
                todo.getTitle(),
                todo.getContents(),
                todo.getCreateAt(),
                todo.getModifiedAt()
        );

    }

}