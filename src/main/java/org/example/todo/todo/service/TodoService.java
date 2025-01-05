package org.example.todo.todo.service;


import org.example.todo.todo.dto.request.CreateTodoRequestDto;
import org.example.todo.todo.dto.request.UpdateTodoRequestDto;
import org.example.todo.todo.dto.response.CreateTodoResponseDto;
import org.example.todo.todo.dto.response.ReadTodoListResponseDto;
import org.example.todo.todo.dto.response.ReadTodoResponseDto;
import org.example.todo.todo.dto.response.UpdateTodoResponseDto;

import java.util.List;

public interface TodoService {
    CreateTodoResponseDto createTodo(CreateTodoRequestDto todoCreateRequestDto);

    List<ReadTodoListResponseDto> getTodoList();

    ReadTodoResponseDto getTodo(Long id);

    UpdateTodoResponseDto updateTodo(Long id, UpdateTodoRequestDto updateTodoRequestDto);

    void deleteTodo(Long id);
}
