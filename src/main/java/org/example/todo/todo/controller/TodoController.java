package org.example.todo.todo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.todo.todo.dto.request.TodoCreateRequestDto;
import org.example.todo.todo.dto.request.TodoModifyRequestDto;
import org.example.todo.todo.dto.response.TodoCreateResponseDto;
import org.example.todo.todo.dto.response.TodoFindResponseDto;
import org.example.todo.todo.dto.response.TodoModifyResponseDto;
import org.example.todo.todo.dto.response.TodosResponseDto;
import org.example.todo.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    // CREATE TODO
    @PostMapping
    public ResponseEntity<TodoCreateResponseDto> createTodoAPI(
            @RequestBody TodoCreateRequestDto todoCreateRequestDto
    ) {
        TodoCreateResponseDto todoCreateResponseDto = todoService.createTodo(todoCreateRequestDto);
        return new ResponseEntity<>((todoCreateResponseDto), HttpStatus.CREATED);
    }

    // READ TODO ALL
    @GetMapping
    public List<TodosResponseDto> findTodosAPI() {
        return todoService.findTodos();
    }

    // REAL TODO SELECT
    @GetMapping("/{todoId}")
    public ResponseEntity<TodoFindResponseDto> findTodoAPI(
            @PathVariable("todoId") Long id
    ) {
        TodoFindResponseDto findTodo = todoService.findById(id);
        return new ResponseEntity<>(findTodo, HttpStatus.OK);
    }

    // MODIFIY TODO ( Title, Contents )
    @PatchMapping("/{todoId}")
    public ResponseEntity<TodoModifyResponseDto> modifyTodoAPI(
            @PathVariable("todoId") Long id,
            @RequestBody TodoModifyRequestDto requestDto
    ) {
        return new ResponseEntity<>(
                todoService.modifyTodo(
                        id,
                        requestDto.getTitle(),
                        requestDto.getContents()
                ),
                HttpStatus.OK
        );
    }


    // DELETE TODO SELECT
    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodoAPI(
            @PathVariable("todoId") Long id
    ) {
        todoService.deleteTodo(id);
        return ResponseEntity.ok("Todo deletion successful");
    }


}
