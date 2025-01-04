package org.example.todo.todo.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.todo.common.DtoMapper;
import org.example.todo.common.exception.InvalidRequestException;
import org.example.todo.todo.dto.request.CreateTodoRequestDto;
import org.example.todo.todo.dto.response.CreateTodoResponseDto;
import org.example.todo.todo.dto.response.ReadTodoResponseDto;
import org.example.todo.todo.dto.response.UpdateTodoResponseDto;
import org.example.todo.todo.entity.Todo;
import org.example.todo.todo.repository.TodoRepository;
import org.example.todo.user.entity.User;
import org.example.todo.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoServiceimpl implements TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;


    // 1. todo CREATE
    @Transactional
    @Override
    public CreateTodoResponseDto createTodo(
            CreateTodoRequestDto todoCreateRequestDto
    ) {
        log.info("1. todoService.createTodo() 실행");
        User foundUser = userRepository.findById(todoCreateRequestDto.userId())
                                       .orElseThrow(() -> new InvalidRequestException("user not found"));

        Todo newTodo = Todo.create(
                todoCreateRequestDto.username(),
                todoCreateRequestDto.title(),
                todoCreateRequestDto.contents()
        );

        Todo savedTodo = todoRepository.save(newTodo);
        return DtoMapper.mapToDto(savedTodo, t -> new CreateTodoResponseDto(
                                          t.getTodoId(),
                                          t.getUsername(),
                                          t.getTitle(),
                                          t.getContents()
                                  )
        );
    }

    // 2. todo READ :: ALL
    @Override
    public List<ReadTodoResponseDto> getTodoList() {
        List<Todo> todoList = todoRepository.findAll();
        return DtoMapper.toDtoList(
                todoList,
                ReadTodoResponseDto::toDto
        );
    }

    // 3. todo READ :: SELECT
    @Override
    public ReadTodoResponseDto getTodo(Long todoId) {

        Todo foundTodo = todoRepository.findById(todoId)
                                       .orElseThrow(() -> new InvalidRequestException("Todo not found"));
        return ReadTodoResponseDto.toDto(foundTodo);
    }

    // 4. todo MODIFY :: TITLE, CONTENTS
    @Override
    @Transactional
    public UpdateTodoResponseDto updateTodo(
            Long todoId,
            String title,
            String contents
    ) {
        Todo foundTodo = todoRepository.findById(todoId)
                                       .orElseThrow(() -> new InvalidRequestException("Todo not found"));

        foundTodo.update(title, contents);

        return DtoMapper.mapToDto(foundTodo, t -> new UpdateTodoResponseDto(
                t.getTodoId(),
                t.getUsername(),
                t.getTitle(),
                t.getContents(),
                t.getUpdatedAt()
        ));
    }

    // 5. todo DELETE
    @Override
    @Transactional
    public void deleteTodo(Long todoId) {

        Todo foundTodo = todoRepository.findById(todoId)
                                       .orElseThrow(() -> new InvalidRequestException("Todo not found"));
        todoRepository.deleteById(todoId);
    }


}
