package org.example.todo.todo.dto.response;


import lombok.Getter;
import org.example.todo.todo.entity.Todo;

import java.time.LocalDateTime;

@Getter
public class TodosResponseDto {

    private final Long todoId;
    private final String username;
    private final String title;
    private final String contents;
    private LocalDateTime createAt;    // 생성 날짜

    public TodosResponseDto(Todo todo) {
        this.todoId = todo.getTodoId();
        this.username = todo.getUsername();
        this.title = todo.getTitle();
        this.contents = todo.getContents();
        this.createAt = todo.getCreateAt();        // BaseEntity에서 상속된 필드
    }

}