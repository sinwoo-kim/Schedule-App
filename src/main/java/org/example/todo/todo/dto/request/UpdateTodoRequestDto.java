package org.example.todo.todo.dto.request;

public record UpdateTodoRequestDto(Long userId, String username, String title, String contents) {

}
