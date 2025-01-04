package org.example.todo.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.todo.user.dto.request.UserRequestDto;
import org.example.todo.user.dto.response.UserResponseDto;
import org.example.todo.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor // 생성자 주입 로직 자동 생성
public class UserController {

    private final UserService userService;

    // 2. READ USERS ALL
    @GetMapping()
    public ResponseEntity<List<UserResponseDto>> getUserListAPI() {
        log.info("findUsersAPI를 실행합니다.");
        List<UserResponseDto> Response = userService.getUserList();
        return ResponseEntity.ok(Response);
    }

    // 3. READ SELECT USER
    @GetMapping("{userId}")
    public ResponseEntity<UserResponseDto> getUserAPI(@PathVariable("userId") Long id) {
        log.info("findUserAPI를 실행합니다.");
        UserResponseDto Response = userService.getUser(id);
        return new ResponseEntity<>(Response, HttpStatus.OK);
    }

    // 4. MODIFY USER (name, email)
    @PatchMapping("/{userId}")
    public ResponseEntity<UserResponseDto> updateUserAPI(
            @PathVariable("userId") Long id,
            @RequestBody UserRequestDto requestDto
    ) {
        log.info("modifyUserAPI를 실행합니다.");
        UserResponseDto Response = userService.updateUser(id, requestDto.getName(), requestDto.getEmail());
        return new ResponseEntity<>(Response, HttpStatus.OK);
    }

    // 5. DELETE USER
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUserAPI(@PathVariable("userId") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deletion successful");
    }
}
