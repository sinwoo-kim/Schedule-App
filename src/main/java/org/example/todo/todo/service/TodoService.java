package org.example.todo.todo.service;


import org.example.todo.todo.dto.request.TodoCreateRequestDto;
import org.example.todo.todo.dto.response.TodoCreateResponseDto;
import org.example.todo.todo.dto.response.TodoFindResponseDto;
import org.example.todo.todo.dto.response.TodoModifyResponseDto;
import org.example.todo.todo.dto.response.TodosResponseDto;

import java.util.List;

public interface TodoService {
    TodoCreateResponseDto createTodo(TodoCreateRequestDto todoCreateRequestDto);

    List<TodosResponseDto> findTodos();

    TodoFindResponseDto findById(Long id);

    TodoModifyResponseDto modifyTodo(Long id, String title, String contents);

    void deleteTodo(Long id);
}
