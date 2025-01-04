package org.example.todo.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.todo.common.exception.InvalidRequestException;
import org.example.todo.config.PasswordEncoder;
import org.example.todo.user.dto.response.UserResponseDto;
import org.example.todo.user.entity.User;
import org.example.todo.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final PasswordEncoder passwordEncoder;
    UserRepository userRepository;

//    /**
//     * LOGIN 메서드
//     * @param loginRequestDto
//     * @return DTO 변환 로직을 사용
//     */
//    @Transactional
//    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
//        // dto -> User 변환 로직 구현
//        User loginRequest = User.toEntityFromLoginRequestDto(loginRequestDto);
//        // 사용자 조회
//        Boolean foundUser = userRepository.existsByEmail(loginRequest.getEmail());
//
//        if(!passwordEncoder.matches(loginRequest.getPassword(),foundUser.getPassword())) {
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
//        }
//        return LoginResponseDto.of(foundUser);
//    }

    // READ :: ALL
    public List<UserResponseDto> findUsers() {
        List<User> userList = userRepository.findAll();
        return userList.stream()
                       .map(UserResponseDto::new)
                       .collect(Collectors.toList());
    }

    // READ :: FIND USER BY ID
    public UserResponseDto findUser(Long userId) {
        User foundUser = userRepository
                .findById(userId)
                .orElseThrow(() -> new InvalidRequestException("user not found"));
        return new UserResponseDto(foundUser);
    }

    // MODIFY
    @Transactional
    public UserResponseDto modifyUser(Long userId, String username, String email) {
        User foundUser = userRepository
                .findById(userId)
                .orElseThrow(() -> new InvalidRequestException("user not found"));
        foundUser.update(username, email);

        return new UserResponseDto(foundUser);
    }

    // DELETE
    @Transactional
    public void deleteUser(Long userId) {
        User foundUser = userRepository
                .findById(userId)
                .orElseThrow(() -> new InvalidRequestException("user not found"));
        userRepository.deleteById(userId);
    }
}
