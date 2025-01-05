package org.example.todo.todo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.todo.common.ApiResponse;
import org.example.todo.todo.dto.request.CreateTodoRequestDto;
import org.example.todo.todo.dto.request.UpdateTodoRequestDto;
import org.example.todo.todo.dto.response.CreateTodoResponseDto;
import org.example.todo.todo.dto.response.ReadTodoListResponseDto;
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

    // 1. CREATE API
    @PostMapping
    public ResponseEntity<ApiResponse<CreateTodoResponseDto>> createTodoAPI(
            @RequestBody CreateTodoRequestDto requestDto
    ) {
        CreateTodoResponseDto response = todoService.createTodo(requestDto);
        ApiResponse apiResponse = ApiResponse.success(HttpStatus.CREATED, "created", response);
        return new ResponseEntity<ApiResponse<CreateTodoResponseDto>>(apiResponse, HttpStatus.CREATED);
    }

    // 2. GET 투두 LIST API
    @GetMapping
    public ResponseEntity<ApiResponse<List<ReadTodoListResponseDto>>> getTodoListAPI() {
        List<ReadTodoListResponseDto> response = todoService.getTodoList();
        ApiResponse apiResponse = ApiResponse.success(HttpStatus.OK, "Got List", response);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    // 3. GET 투두 API
    @GetMapping("/{todoId}")
    public ResponseEntity<ApiResponse<ReadTodoResponseDto>> getTodoAPI(
            @PathVariable("todoId") Long id
    ) {
        ReadTodoResponseDto response = todoService.getTodo(id);
        ApiResponse apiResponse = ApiResponse.success(HttpStatus.OK, "Got todo", response);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    // 4. UPDATE 투두 API
    @PatchMapping("/{todoId}")
    public ResponseEntity<ApiResponse<UpdateTodoResponseDto>> updateTodoAPI(
            @PathVariable("todoId") Long id,
            @RequestBody UpdateTodoRequestDto requestDto
    ) {
        UpdateTodoResponseDto response = todoService.updateTodo(id, requestDto);
        ApiResponse apiResponse = ApiResponse.success(HttpStatus.OK, "updated", response);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    // 5. DELETE 투두 API
    @DeleteMapping("/{todoId}")
    public ResponseEntity<ApiResponse> deleteTodoAPI(
            @PathVariable("todoId") Long id
    ) {
        todoService.deleteTodo(id);
        ApiResponse apiResponse = ApiResponse.success(HttpStatus.OK, "deleted", null);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }


}
