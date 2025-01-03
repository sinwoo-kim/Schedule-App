package org.example.todo.todo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TodoModifyRequestDto {

    private Long userId;
    private String username;
    private String title;
    private String contents;
}
