package org.example.todo.todo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.todo.common.ApiResponse;
import org.example.todo.todo.dto.request.CreateTodoRequestDto;
import org.example.todo.todo.dto.request.UpdateTodoRequestDto;
import org.example.todo.todo.dto.response.CreateTodoResponseDto;
import org.example.todo.todo.dto.response.ReadTodoResponseDto;
import org.example.todo.todo.dto.response.UpdateTodoResponseDto;
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
    public ResponseEntity<ApiResponse<CreateTodoResponseDto>> createTodoAPI(
            @RequestBody CreateTodoRequestDto todoCreateRequestDto
    ) {
        CreateTodoResponseDto response = todoService.createTodo(todoCreateRequestDto);
        ApiResponse apiResponse = ApiResponse.success(HttpStatus.CREATED, "created", response);
        return new ResponseEntity<ApiResponse<CreateTodoResponseDto>>(apiResponse, HttpStatus.CREATED);
    }

    // READ TODO ALL
    @GetMapping
    public List<ReadTodoResponseDto> getTodoListAPI() {
        return todoService.getTodoList();
    }

    // REAL TODO SELECT
    @GetMapping("/{todoId}")
    public ResponseEntity<ReadTodoResponseDto> getTodoAPI(
            @PathVariable("todoId") Long id
    ) {
        ReadTodoResponseDto response = todoService.getTodo(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // MODIFIY TODO ( Title, Contents )
    @PatchMapping("/{todoId}")
    public ResponseEntity<UpdateTodoResponseDto> updateTodoAPI(
            @PathVariable("todoId") Long id,
            @RequestBody UpdateTodoRequestDto requestDto
    ) {
        return new ResponseEntity<>(
                todoService.updateTodo(
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
