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

    // READ :: ALL
    public List<UserResponseDto> getUserList() {
        List<User> foundUserList = userRepository.findAll();
        return foundUserList.stream()
                            .map(UserResponseDto::toDto)
                            .collect(Collectors.toList());
    }

    // READ :: FIND USER BY ID
    public UserResponseDto getUser(Long userId) {
        User foundUser = userRepository.findById(userId)
                                       .orElseThrow(() -> new InvalidRequestException("user not found"));
        return UserResponseDto.toDto(foundUser);
    }

    // MODIFY
    @Transactional
    public UserResponseDto updateUser(Long userId, String username, String email) {
        User foundUser = userRepository.findById(userId)
                                       .orElseThrow(() -> new InvalidRequestException("user not found"));
        foundUser.update(username, email);
        return UserResponseDto.toDto(foundUser);
    }

    // DELETE
    @Transactional
    public void deleteUser(Long userId) {
        User foundUser = userRepository.findById(userId)
                                       .orElseThrow(() -> new InvalidRequestException("user not found"));
        userRepository.deleteById(userId);
    }
}
