package org.example.todo.user.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.todo.common.InvalidRequestException;
import org.example.todo.config.PasswordEncoder;
import org.example.todo.user.dto.request.LoginRequestDto;
import org.example.todo.user.dto.request.SignUpRequestDto;
import org.example.todo.user.dto.response.LoginResponseDto;
import org.example.todo.user.dto.response.SignUpResponseDto;
import org.example.todo.user.dto.response.UserResponseDto;
import org.example.todo.user.entity.User;
import org.example.todo.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    UserRepository userRepository;

    /**
     * SIGN UP 메서드
     * @param signUpRequestDto
     * @return Dto 변환 로직을 사용
     */
    @Transactional
    public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto) {

        // 비밀번호 암호화
        String rawPassword = signUpRequestDto.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        signUpRequestDto.setPassword(encodedPassword);

        // Dto 변환 로직 사용
        User newUser = User.createFromSignUpDto(signUpRequestDto);
        User savedUser = userRepository.save(newUser);
        return SignUpResponseDto.of(savedUser);
    }

    /**
     * LOGIN 메서드
     * @param loginRequestDto
     * @return DTO 변환 로직을 사용
     */
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        // dto -> User 변환 로직 구현
        User loginRequest = User.toEntityFromLoginRequestDto(loginRequestDto);
        // 사용자 조회
        User findLoginUser = userRepository.findByEmail(loginRequest.getEmail());

        if(!passwordEncoder.matches(loginRequest.getPassword(),findLoginUser.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }
        return LoginResponseDto.of(findLoginUser);
    }

    // READ :: ALL
    public List<UserResponseDto> findUsers() {
        List<User> userList = userRepository.findAll();
        return userList.stream().map(UserResponseDto::new).collect(Collectors.toList());
    }

    // READ :: FIND USER BY ID
    public UserResponseDto findUser(Long userId) {
        User foundUser = userRepository
                .findById(userId).orElseThrow(() -> new InvalidRequestException("user not found"));
        return new UserResponseDto(foundUser);
    }

    // MODIFY
    @Transactional
    public UserResponseDto modifyUser(Long userId, String username, String email) {
        User foundUser = userRepository
                .findById(userId).orElseThrow(() -> new InvalidRequestException("user not found"));
        foundUser.update(username, email);

        return new UserResponseDto(foundUser);
    }

    // DELETE
    public void deleteUser(Long userId) {
        User foundUser = userRepository
                .findById(userId).orElseThrow(() -> new InvalidRequestException("user not found"));
        userRepository.deleteById(userId);
    }
}
