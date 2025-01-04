package org.example.todo.todo.dto.response;


import org.example.todo.todo.entity.Todo;

public record CreateTodoResponseDto(
        Long todoId,
        String username,
        String title,
        String contents
) {
    public static CreateTodoResponseDto toDto(Todo todo) {
        return new CreateTodoResponseDto(
                todo.getTodoId(),
                todo.getUsername(),
                todo.getTitle(),
                todo.getContents()
        );
    }

}