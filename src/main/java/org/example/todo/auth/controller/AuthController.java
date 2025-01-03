package org.example.todo.auth.controller;

import lombok.RequiredArgsConstructor;
import org.example.todo.auth.dto.request.SignUpRequestDto;
import org.example.todo.auth.dto.response.SignUpResponseDto;
import org.example.todo.auth.service.AuthService;
import org.example.todo.common.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    // jwt 토큰

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<SignUpResponseDto>> signUpAPI(@RequestBody SignUpRequestDto requestDto) {
        SignUpResponseDto responseDto = authService.signUp(requestDto);
        ApiResponse apiResponse = ApiResponse.success(HttpStatus.CREATED, "signup",responseDto);
        return new ResponseEntity<ApiResponse<SignUpResponseDto>>(apiResponse, HttpStatus.CREATED);
    }


}
