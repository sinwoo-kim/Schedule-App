package org.example.todo.todo.dto.response;


import org.example.todo.todo.entity.Todo;

public record TodoCreateResponseDto(
        Long todoId,
        String username,
        String title,
        String contents
) {
    public static TodoCreateResponseDto toDto(Todo todo) {
        return new TodoCreateResponseDto(
                todo.getTodoId(),
                todo.getUsername(),
                todo.getTitle(),
                todo.getContents()
        );
    }

}