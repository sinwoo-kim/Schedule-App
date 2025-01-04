package org.example.todo.todo.dto.response;

import java.time.LocalDateTime;

public record UpdateTodoResponseDto(Long todoId, String username, String title, String contents,
                                    LocalDateTime updatedAt) {

}
