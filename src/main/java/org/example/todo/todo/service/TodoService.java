package org.example.todo.todo.service;


import org.example.todo.todo.dto.request.CreateTodoRequestDto;
import org.example.todo.todo.dto.response.CreateTodoResponseDto;
import org.example.todo.todo.dto.response.ReadTodoResponseDto;
import org.example.todo.todo.dto.response.UpdateTodoResponseDto;

import java.util.List;

public interface TodoService {
    CreateTodoResponseDto createTodo(CreateTodoRequestDto todoCreateRequestDto);

    List<ReadTodoResponseDto> getTodoList();

    ReadTodoResponseDto getTodo(Long id);

    UpdateTodoResponseDto updateTodo(Long id, String title, String contents);

    void deleteTodo(Long id);
}
