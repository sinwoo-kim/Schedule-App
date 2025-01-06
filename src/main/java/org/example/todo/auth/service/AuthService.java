package org.example.todo.auth.service;

import lombok.RequiredArgsConstructor;
import org.example.todo.auth.dto.request.SignUpRequestDto;
import org.example.todo.auth.dto.response.SignUpResponseDto;
import org.example.todo.exception.data.DataAlreadyExistsException;
import org.example.todo.config.PasswordEncoder;
import org.example.todo.user.entity.User;
import org.example.todo.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    // jwt

    @Transactional
    public SignUpResponseDto signUp(SignUpRequestDto requestDto) {

        // 비즈니스 규칙 : 이메일이 중복되어서는 안된다.
        if (userRepository.existsByEmail(requestDto.email())) {
            throw new DataAlreadyExistsException("이메일이 이미 존재합니다.");
        }
        // 비밀번호 암호화
        String rawPassword = requestDto.password();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        // get 권한

        // 생성
        User newUser = User.create(requestDto.email(), requestDto.password(), requestDto.name());

        // 저장
        User savedUser = userRepository.save(newUser);

        // 토큰 생성 시점?

        // 반환
        return SignUpResponseDto.toDto(savedUser);
    }
}
